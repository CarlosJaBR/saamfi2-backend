package co.edu.icesi.dev.saamfi.controller.security;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.logic.implementation.authentication.SaamfiUserServiceImpl;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiSystemService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Class to handdle JWT token generation and validation
 *
 * @author Luis Miguel Paz V & Kliver Daniel Giron
 *
 */

@Component
@SuppressWarnings("all")
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	/**
	 * Path to private key file
	 */
	private static final String PATH_PRIVATE = "private_key.der";

	/**
	 * Path to public key file
	 */
	private static final String PATH_PUBLIC = "public_key.der";

	private static final String ROLE_KEYS = "role";

	private static final String INSTITUTION_CLAIM = "institution";

	private static final String SYSTEM_CLAIM = "system";

	/**
	 * Time given to the token in minutes
	 */
	public static final long expiryTime = 10;

	private static final String USERNAME_CLAIM = "username";

	private static final String PERSID_CLAIM = "persId";

	/**
	 * private key atribute
	 */
	private PrivateKey privateKey;

	/**
	 * public key atribute
	 */
	private PublicKey publicKey;

	private byte[] publicKeyBytes;

	private SaamfiUserServiceImpl userservice;

	private SaamfiSystemService systemService;

	public JwtTokenProvider(SaamfiUserServiceImpl userservice,
			SaamfiSystemService saamfiSystemService) {
		this.userservice = userservice;
		this.systemService = saamfiSystemService;
		try {
			privateKey = getPrivateKey(PATH_PRIVATE);
			publicKey = getPublicKey(PATH_PUBLIC);
		} catch (Exception e) {
			logger.error("Error reading keys");
		}

	}

	/**
	 * This method generates a JWT token for a valid authentication.
	 *
	 * @param authentication
	 * @return
	 */
	public String generateToken(Authentication authentication, long instId, long sysid) {

		// Takes current time
		Instant now = Instant.now();

		String username;
		String authorities = null;
		// Get info for the token when the authentication was done using LDAP

		long userId = -1L;

		UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) authentication;
		username = userPrincipal.getName();
		userId = userservice.getUser(username, instId).getUserId();
		authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		long tokenTimeOut = expiryTime;
		try {
			SaamfiSystem system = systemService.getSystem(sysid);
			tokenTimeOut = system.getSysNormalTokenTimeout() / 60;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Jwts.builder().claim(PERSID_CLAIM, userId + "").claim(ROLE_KEYS, authorities).claim(SYSTEM_CLAIM, sysid)
				.claim(INSTITUTION_CLAIM, instId).claim(USERNAME_CLAIM, username)
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plus(tokenTimeOut, ChronoUnit.MINUTES)))
				.signWith(privateKey, SignatureAlgorithm.RS256).compact();

	}

	UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth,
			final UserDetails userDetails) {

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		return new UsernamePasswordAuthenticationToken(userDetails, token.trim(), authorities);

	}

	public Collection<SimpleGrantedAuthority> getRolesFromJWT(String token) {
		final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(publicKey).build();
		final Jws claimsJws = jwtParser.parseClaimsJws(token);
		final Claims claims = (Claims) claimsJws.getBody();
		Stream<SimpleGrantedAuthority> stream = Arrays.stream(claims.get(ROLE_KEYS).toString().split(","))
				.map(SimpleGrantedAuthority::new);
		Collection<SimpleGrantedAuthority> authorities = null;
		try {
			authorities = stream.collect(Collectors.toList());
		} catch (Exception e) {
			authorities = Collections.emptyList();
		}
		return authorities;

	}

	public long getInstIdFromJWT(String token)
			throws IllegalArgumentException, ExpiredJwtException, SignatureException {

		Claims claims = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();
		int instId = (int) claims.get(INSTITUTION_CLAIM);

		return instId;
	}

	/**
	 *
	 * Method to read private keys form .der files
	 *
	 * @param filename path to private key file
	 * @return the PrivateKey object from the java.security package.
	 * @throws Exception when the key can not be read
	 */
	// Source:
	// https://stackoverflow.com/questions/11410770/load-rsa-public-key-from-file/29372812
	public PrivateKey getPrivateKey(String filename) throws Exception {

		byte[] keyBytes = null;

		String externalPath = Environment.getProperties().getProperty("catalina.home") + File.separator + "conf"
				+ File.separator + "saamfi" + File.separator + filename;
		try {
			File file = ResourceUtils.getFile(externalPath);
			keyBytes = Files.readAllBytes(file.toPath());
		} catch (Exception e2) {
			try {
				File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + filename);
				keyBytes = Files.readAllBytes(file.toPath());
			} catch (Exception e) {
				// Exceptional execution for jar deployments
				logger.info(Paths.get(filename).toString());
				keyBytes = Files.readAllBytes(Paths.get(filename));
			}
		}

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	/**
	 *
	 * Method to read public keys form .der files
	 *
	 * @param filename path to public key file
	 * @return the PublicKey object from the java.security package.
	 * @throws Exception when the key can not be read
	 */

	public PublicKey getPublicKey(String filename) throws Exception {

		byte[] keyBytes = null;
		String externalPath = Environment.getProperties().getProperty("catalina.home") + File.separator + "conf"
				+ File.separator + "saamfi" + File.separator + filename;
		try {
			File file = ResourceUtils.getFile(externalPath);
			keyBytes = Files.readAllBytes(file.toPath());
		} catch (Exception e2) {
			try {
				File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + filename);
				keyBytes = Files.readAllBytes(file.toPath());
			} catch (Exception e) {
				// Exceptional execution for jar deployments
				logger.info(Paths.get(filename).toString());
				keyBytes = Files.readAllBytes(Paths.get(filename));
			}
		}
		publicKeyBytes = keyBytes;
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public long getSysIdFromJWT(String token) throws IllegalArgumentException, ExpiredJwtException, SignatureException {

		Claims claims = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();
		int instId = (int) claims.get(SYSTEM_CLAIM);

		return instId;
	}

	public long getUserIdFromJWT(String token)
			throws IllegalArgumentException, ExpiredJwtException, SignatureException {

		Claims claims = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();

		return Long.parseLong(claims.get(PERSID_CLAIM).toString());
	}

	public String getUsernameFromJWT(String token)
			throws IllegalArgumentException, ExpiredJwtException, SignatureException {

		Claims claims = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();
		String username = (String) claims.get(USERNAME_CLAIM);

		return username;
	}

	/**
	 *
	 * @param authToken to validate
	 * @return if the provided token is valid
	 */
	public boolean validateToken(String authToken) {
		try {

			Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *
	 * @param authToken to validate
	 * @return if the provided token is valid
	 */
	public boolean validateTokenWithUser(String authToken, UserDetails subject) {
		try {

			return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(authToken).getBody()
					.get(PERSID_CLAIM)
					.toString().equals(subject.getUsername());

		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public byte[] getPublicKey() {
		return publicKeyBytes;
	}
}