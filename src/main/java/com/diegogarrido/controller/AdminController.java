package com.diegogarrido.controller;

import java.security.Principal;

import com.diegogarrido.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.diegogarrido.service.UserService;
import com.diegogarrido.util.Role;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService uService;

    @GetMapping("/users")
    public Iterable<User> admin(Principal principal) {
        if (principal.getName() == null || !uService.usernameExists(principal.getName())) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid user");
        }
        return uService.findAll(uService.findByUsername(principal.getName()).getRole());
    }

    @GetMapping("/users/delete/{id}")
    public void deleteUser(Principal principal, @PathVariable Integer id) {
        if (principal.getName() == null || !uService.usernameExists(principal.getName())) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid user");
        }
        if (!uService.userExist(id)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "User to delete does not exists");
        }
        if ((uService.findById(id).getRole().equals(Role.ADMIN) && uService.findByUsername(principal.getName()).getRole().equals(Role.ADMIN)) || uService.findById(id).getRole().equals(Role.SUPER_ADMIN)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "Not enough privileges");
        }
        uService.deleteUser(uService.findById(id));
    }

    @GetMapping("/users/make_admin/{id}")
    public User makeAdmin(Principal principal, @PathVariable Integer id) {
        if (principal.getName() == null || !uService.usernameExists(principal.getName())) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid user");
        }
        if (!uService.userExist(id)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "User to promote does not exists");
        }
        if (uService.findById(id).getRole().equals(Role.ADMIN)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "User is already admin");
        }
        if (uService.findById(id).getRole().equals(Role.SUPER_ADMIN)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "Not enough privileges");
        }
        return uService.changeRole(uService.findById(id), Role.ADMIN);
    }

    @GetMapping("/super/un_admin/{id}")
    public User unAdmin(Principal principal, @PathVariable Integer id) {
        if (principal.getName() == null || !uService.usernameExists(principal.getName())) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid user");
        }
        if (!uService.userExist(id)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "User to promote does not exists");
        }
        if (uService.findById(id).getRole().equals(Role.USER)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "User is not an admin");
        }
        if (uService.findById(id).getRole().equals(Role.SUPER_ADMIN)) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED, "Not enough privileges");
        }
        return uService.changeRole(uService.findById(id), Role.USER);
    }

}
