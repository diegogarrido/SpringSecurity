package com.codingdojo.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.codingdojo.java.model.Role;
import com.codingdojo.java.model.dao.RoleDAO;

@SpringBootConfiguration
public class Initialization {

	@Bean
	public CommandLineRunner initializeRoles(RoleDAO rdao) {
		return (args) -> {
			Role r = new Role();
			r.setName("ROLE_USER");
			rdao.save(r);

			r = new Role();
			r.setName("ROLE_ADMIN");
			rdao.save(r);
		};
	}
}
