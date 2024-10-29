package co.edu.icesi.dev.saamfi;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * @author Imported from MEDVED Project - (Capstone Project 2 2020-1 -
 *         Universidad Icesi, Cali - Colombia)
 *
 */
@Configuration
public class TimeConfig {

	private static final String dateFormat = "yyyy-MM-dd";
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	@Value("${application.timezone}")
	private String timezone;

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			builder.simpleDateFormat(dateTimeFormat);
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			TimeZone.setDefault(TimeZone.getTimeZone(timezone));
			builder.timeZone(TimeZone.getDefault());
		};
	}

}