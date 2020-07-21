package com.diegogarrido.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Initialization {

	@Bean
	public CommandLineRunner initialize() {
		return (args) -> {
			//Insert data here
		};
	}
}
