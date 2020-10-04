package com.diegogarrido.service;

import java.util.ArrayList;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegogarrido.model.User;
import com.diegogarrido.model.dao.UserDAO;
import com.diegogarrido.util.Role;

@Service
public class UserService {

    @Autowired
    private UserDAO uDao;

    public User registerUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        if (uDao.count() == 0) {
            user.setRole(Role.SUPER_ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        return uDao.save(user);
    }

    public User changeRole(User user, Role role) {
        if (!user.getRole().equals(role)) {
            user.setRole(role);
            return uDao.save(user);
        } else {
            return user;
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void deleteUser(User user) {
        uDao.delete(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = uDao.findByUsername(username);
        return user.orElse(null);
    }

    public User findById(Integer id) {
        Optional<User> u = uDao.findById(id);
        return u.orElse(null);
    }

    public boolean userExist(Integer id) {
        return uDao.findById(id).isPresent();
    }

    public boolean emailExists(String email) {
        return uDao.findByEmail(email).isPresent();
    }

    public boolean usernameExists(String userName) {
        return uDao.findByUsername(userName).isPresent();
    }

    public Iterable<User> findAll(Role role) {
        ArrayList<Role> rolesToFind = new ArrayList<>();
        rolesToFind.add(Role.USER);
        if (role.equals(Role.SUPER_ADMIN)) {
            rolesToFind.add(Role.ADMIN);
        }
        return uDao.findByRoleIn(rolesToFind);
    }

    public boolean authenticateUser(String userName, String password) {
        User user = findByUsername(userName);
        if (user == null) {
            return false;
        } else {
            return BCrypt.checkpw(password, user.getPassword());
        }
    }
}
