package co.edu.icesi.dev.saamfi.controller.utils;

import java.security.spec.KeySpec;
import java.util.Collection;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import co.edu.icesi.dev.saamfi.controller.security.JwtTokenProvider;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionService;

@Controller
public class ControllerAuthorizationValidator {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAuthorizationValidator.class);

	private static JwtTokenProvider jwtTokenProvider;

	private static SaamfiPermissionService permissionService;

	public static String getPasswordHash(String pwd) {
		try {
			Random random = new Random();
			random.setSeed(152389161l);
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			String myHash = DatatypeConverter.printHexBinary(hash).toUpperCase();

			return myHash;
		} catch (Exception e) {
			logger.error("Error while trying to encrypt password: " + e.getMessage());
			return pwd;
		}
	}

	/**
	 * Method to validate institution provided against token institution.
	 *
	 * @param instid
	 * @return
	 */
	public static boolean validateInstitution(long instid) {
		String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		long instId = jwtTokenProvider.getInstIdFromJWT(token);

		return instId == instid;
	}

	/**
	 * Method to validate institution and systemid provided against token
	 * institution and personId.
	 *
	 * @param instid
	 * @param personid
	 * @return
	 */
	public static boolean validateInstitutionAndSystem(long instid, long sysid) {
		String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		long instId = jwtTokenProvider.getInstIdFromJWT(token);
		long sysId = jwtTokenProvider.getSysIdFromJWT(token);
		return instId == instid && sysId == sysid;
	}

	/**
	 * Method to validate institution and personId provided against token
	 * institution and personId.
	 *
	 * @param instid
	 * @param personid
	 * @return
	 */
	public static boolean validateInstitutionAndSystemAndPerson(long instid, long sysid, long personid) {
		String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		long instIdT = jwtTokenProvider.getInstIdFromJWT(token);
		long sysIdT = jwtTokenProvider.getSysIdFromJWT(token);
		long userId = jwtTokenProvider.getUserIdFromJWT(token);
		if (instIdT == instid && userId == personid && sysIdT == sysid) // Si personid existe en tokens.persons -> en 4
																		// modulos
			return true;
		return false;
	}

	/**
	 * Method to encrypt text
	 *
	 * @param pwd text to encrypt
	 * @return
	 */

	@Autowired
	public ControllerAuthorizationValidator(JwtTokenProvider jwtTokenProvider,
			SaamfiPermissionService permissionService) {
		ControllerAuthorizationValidator.jwtTokenProvider = jwtTokenProvider;
		ControllerAuthorizationValidator.permissionService = permissionService;
	}

	public static boolean hasPermission(String permName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
		boolean isGlob = auths.stream().anyMatch(key -> key.getAuthority().contains(permName));

		return isGlob;
	}

	public static boolean hasPermission(String permName, long instid) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasInsRole = permissionService.userHasPermissionInInst(authentication.getName(), instid,
				permName);
		return hasInsRole;
	}

	public static boolean hasPermission(String permName, long instid, long sysid) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasSysRole = permissionService.userHasPermissionInSystemAndInst(authentication.getName(), sysid,
				instid, permName);

		return hasSysRole;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
