package co.edu.icesi.dev.saamfi.delegate.utils;

import java.util.Map;

import org.springframework.http.HttpHeaders;

public class HttpUtils {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static HttpHeaders createHeaders(Map<String, String> headers) {
		return new HttpHeaders() {
			{
				for (Map.Entry<String, String> entry : headers.entrySet())
					set(entry.getKey(), entry.getValue());

			}
		};
	}

	public static String getAuthorizationValue(String token) {
		return "Bearer " + token;
	}
}
