package com.example.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class HostelApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HostelApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Setting Default TimeZone to Kuwait
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kuwait"));
	}

	/**
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
