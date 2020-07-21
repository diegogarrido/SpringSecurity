package com.diegogarrido.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diegogarrido.service.UserService;
import com.diegogarrido.util.Role;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService uService;

	@GetMapping("")
	public String admin(Principal principal, Model model) {
		if (principal.getName() != null && uService.usernameExists(principal.getName())) {
			model.addAttribute("user", uService.findByUsername(principal.getName()));
			model.addAttribute("users", uService.findAll());
			return "admin";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/delete_user")
	public String deleteUser(Integer userId) {
		if (uService.userExist(userId) && !uService.findById(userId).isAdmin()) {
			uService.deleteUser(uService.findById(userId));
		}
		return "redirect:/admin";
	}

	@GetMapping("/make_admin")
	public String makeAdmin(Integer userId) {
		if (uService.userExist(userId)) {
			uService.addRole(uService.findById(userId), Role.ADMIN);
		}
		return "redirect:/admin";
	}

	@GetMapping("/super/un_admin")
	public String unAdmin(Integer adminId) {
		if (uService.userExist(adminId)) {
			uService.removeRole(uService.findById(adminId), Role.ADMIN);
		}
		return "redirect:/admin";
	}

	@GetMapping("/super/delete_admin")
	public String deleteAdmin(Integer adminId) {
		if (uService.userExist(adminId) && uService.findById(adminId).isAdmin()) {
			uService.deleteUser(uService.findById(adminId));
		}
		return "redirect:/admin";
	}

}
