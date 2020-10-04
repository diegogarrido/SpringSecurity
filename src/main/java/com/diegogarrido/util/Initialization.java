package com.diegogarrido.util;

import com.diegogarrido.model.User;
import com.diegogarrido.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Initialization {

    @Bean
    public CommandLineRunner initialize(UserService uService) {
        return (args) -> {
            User u = new User();
            u.setUsername("super");
            u.setEmail("super@mail.com");
            u.setPassword("123123");
            uService.registerUser(u);

            u = new User();
            u.setUsername("admin");
            u.setEmail("admin@mail.com");
            u.setPassword("123123");
            uService.registerUser(u);
            uService.changeRole(u, Role.ADMIN);

            u = new User();
            u.setUsername("user");
            u.setEmail("user@mail.com");
            u.setPassword("123123");
            uService.registerUser(u);
        };
    }
}
