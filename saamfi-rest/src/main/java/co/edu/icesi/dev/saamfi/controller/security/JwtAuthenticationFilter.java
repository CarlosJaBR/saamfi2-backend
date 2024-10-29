package co.edu.icesi.dev.saamfi.controller.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@SuppressWarnings("all")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	/**
	 * String to retrieve authentication in header
	 */
	public static final String HEADER_STRING = "Authorization";

	/**
	 * String to remove the token prefix
	 */
	public static final String TOKEN_PREFIX = "Bearer";

	/**
	 * provider of JWT methods
	 */
	@Autowired
	private JwtTokenProvider jwtTokenUtil;

	@Override
	@SuppressWarnings("all")
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(HEADER_STRING);
		String username = null;
		String authToken = null;

		if (header != null && !header.equals("Bearer undefined") && header.startsWith(TOKEN_PREFIX)) {
			authToken = header.replace(TOKEN_PREFIX, "");
			if (!authToken.trim().equals("null")) {
				try {
					username = jwtTokenUtil.getUsernameFromJWT(authToken);
				} catch (SignatureException e) {
					logger.error("Authentication Failed. Username or Password not valid.");
				} catch (IllegalArgumentException e) {
					logger.error("an error occured during getting username from token", e);
				} catch (ExpiredJwtException e) {
					logger.warn("the token is expired and not valid anymore", e);
				}

			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (username != null && auth == null) {
			Collection<SimpleGrantedAuthority> roles = jwtTokenUtil.getRolesFromJWT(authToken);
			if (roles == null) {
				roles = Collections.emptyList();
			}

			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", roles);

			if (jwtTokenUtil.validateToken(authToken)) {
				UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken,
						SecurityContextHolder.getContext().getAuthentication(), userDetails);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("usr:" + username + ", module auth, path:" + request.getServletPath());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);

	}

}
