package com.diegogarrido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.diegogarrido.model.User;
import com.diegogarrido.service.UserService;
import com.diegogarrido.util.UserValidator;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class UserController {

	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator uValidator;

	@PostMapping("/register")
	public User register(@RequestBody User user, BindingResult result) {
		uValidator.validate(user, result);
		if (result.hasErrors()) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, result.getAllErrors().get(0).getDefaultMessage());
		}
		return uService.registerUser(user);
	}

}
