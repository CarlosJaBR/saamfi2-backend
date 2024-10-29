package co.edu.icesi.dev.saamfi.delegate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthDelegateApplication {

	// @LoadBalanced
	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}

}
