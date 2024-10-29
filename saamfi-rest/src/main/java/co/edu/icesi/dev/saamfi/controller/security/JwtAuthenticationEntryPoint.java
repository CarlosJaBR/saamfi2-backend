package co.edu.icesi.dev.saamfi.controller.security;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Class to handle unauthorized requests
 *
 * @author Luis Miguel Paz
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		HashMap<String, String> response = new HashMap<String, String>();
		e.printStackTrace();
		if (e instanceof BadCredentialsException) {
			response.put("message", "Bad credentials");
		} else if (e instanceof InternalAuthenticationServiceException) {
			if (e.getMessage().contains("Connection timed out")) {
				response.put("message", "Connection timed out");

			}

			else if (e.getMessage().contains("Connection refused")) {
				response.put("message", "Connection refused");
			}

		}

		else {
			response.put("message", e.getMessage());
		}

		JSONObject responseJson = new JSONObject(response);
		httpServletResponse.getWriter().write(responseJson.toJSONString());
	}
}