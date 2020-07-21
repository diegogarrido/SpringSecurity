package com.codingdojo.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Initialization {

	@Bean
	public CommandLineRunner initializeRoles() {
		return (args) -> {
			
		};
	}
}
