package com.codingdojo.java.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingdojo.java.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService uService;

	@GetMapping({ "/", "/home" })
	public String home(Principal principal, Model model) {
		model.addAttribute("user", uService.findByUsername(principal.getName()));
		return "home";
	}

	@GetMapping("/admin")
	public String admin(Principal principal, Model model) {
		model.addAttribute("user", uService.findByUsername(principal.getName()));
		return "admin";
	}

}
