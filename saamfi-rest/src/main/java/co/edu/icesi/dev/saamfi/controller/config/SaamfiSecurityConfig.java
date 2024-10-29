package co.edu.icesi.dev.saamfi.controller.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import co.edu.icesi.dev.saamfi.controller.security.DatabaseAuthenticationProvider;
import co.edu.icesi.dev.saamfi.controller.security.JwtAuthenticationEntryPoint;
import co.edu.icesi.dev.saamfi.controller.security.JwtAuthenticationFilter;

/**
 * Class to manage web security
 *
 * @author Luis Miguel Paz
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SaamfiSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Database authentication provider, responsible of handling database
	 * authentication.
	 */
	private DatabaseAuthenticationProvider databaseAuthenticationProvider;

	/**
	 * @param databaseAuthenticationProvider the databaseAuthenticationProvider to
	 *                                       set
	 */
	@Autowired
	public void setDatabaseAuthenticationProvider(DatabaseAuthenticationProvider databaseAuthenticationProvider) {
		this.databaseAuthenticationProvider = databaseAuthenticationProvider;
	}

	/**
	 * Authentication entry point for unauthorized request attempts
	 */
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	/**
	 * @param unauthorizedHandler the unauthorizedHandler to set
	 */
	@Autowired
	public void setUnauthorizedHandler(JwtAuthenticationEntryPoint unauthorizedHandler) {
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Method to get the authentication filter
	 *
	 * @return new JwtAuthenticationFilter
	 */
	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationFilter();
	}

	/**
	 * Configuration of multiple authentication methods. Institutional LDAP,
	 * database and embedded LDAP executed in that order.
	 */

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(databaseAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Configuration of cors and csfr policies. Configuration of authorized paths
		// without authorization.
		http.cors().and().csrf().disable()
				// .addFilterBefore(new
				// co.edu.icesi.dev.saamfi.controller.security.CorsFilter(),
				// ChannelProcessingFilter.class)
				.authorizeRequests()
				.antMatchers("/public/institutions/{instid}/systems/{sysid}/authentication/login",
						"/public/institutions/{instid}/recovery-password/generateCode",
						"/public/institutions/{instid}/recovery-password/change-password",
						"/public/institutions/{instid}/recovery-password/validateCode",
						"/public/institutions/{instid}/systems/{sysid}/users/**", "/internal/**", "/public/**",
						"/public/institutions/{instid}/recaptcha/**", "/actuator/**", "/public/institutions/**",
						"/public/systems/**")
				.permitAll().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Filter to handle token authorized request
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// http.csrf().disable().cors().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// .authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
	}

	/**
	 * Configuration of cors security policies
	 *
	 * @return
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/**
	 * Configuration of cors security policies
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<?> simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// * URL below needs to match the Vue client URL and port *s
		config.setAllowedOriginPatterns(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}