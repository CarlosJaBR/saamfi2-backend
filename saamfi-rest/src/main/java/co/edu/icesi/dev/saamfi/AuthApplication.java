package co.edu.icesi.dev.saamfi;

import java.io.File;
import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		String configPath = Environment.getProperties().getProperty("catalina.home")
				+ File.separator + "conf"
				+ File.separator + "saamfi" + File.separator +
				"saamfi-application.properties";
		//
		logger.info("\n\n\nConfigpath: " + configPath);
		logger.info("\n\n\nStarting to run Spring boot app...");
		Properties props = new Properties();
		props.setProperty("spring.config.location", configPath); // set the config
		// file to use
		application.application().setDefaultProperties(props);
		return application.sources(AuthApplication.class);
	}

}
