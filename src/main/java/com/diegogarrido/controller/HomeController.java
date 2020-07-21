package com.diegogarrido.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.diegogarrido.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService uService;

	@GetMapping({ "/", "/home" })
	public String home(Principal principal, Model model) {
		if (principal.getName() != null && uService.usernameExists(principal.getName())) {
			model.addAttribute("user", uService.findByUsername(principal.getName()));
			return "home";
		} else {
			return "redirect:/login";
		}
	}

}
