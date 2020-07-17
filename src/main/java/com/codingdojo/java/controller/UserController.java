package com.codingdojo.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.java.model.User;
import com.codingdojo.java.service.UserService;
import com.codingdojo.java.util.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator uValidator;

	@GetMapping("/registration")
	public String register(Model model) {
		return "registration";
	}

	@PostMapping("/registration")
	public String registerPost(User user, Model model, BindingResult result) {
		uValidator.validate(user, result);
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getAllErrors());
			return register(model);
		}
		uService.registerUser(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", "Invalid Credentials, Please try again.");
		}
		return "login";
	}

}
