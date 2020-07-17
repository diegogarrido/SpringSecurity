package com.codingdojo.java.service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.java.model.User;
import com.codingdojo.java.model.dao.RoleDAO;
import com.codingdojo.java.model.dao.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO uDao;
	@Autowired
	private RoleDAO rDao;

	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		if (uDao.count() == 0) {
			user.getRoles().add(rDao.findByName("ROLE_ADMIN").get());
		} else {
			user.getRoles().add(rDao.findByName("ROLE_USER").get());
		}
		return uDao.save(user);
	}

	public User registerUser(User user, Boolean admin) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		user.getRoles().add(rDao.findByName("ROLE_ADMIN").get());
		return uDao.save(user);
	}

	public User findByUsername(String username) {
		Optional<User> user = uDao.findByUsername(username);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	public User findById(Integer id) {
		Optional<User> u = uDao.findById(id);
		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}

	public boolean userExists(String userName) {
		return uDao.findByUsername(userName).isPresent();
	}

	public boolean authenticateUser(String userName, String password) {
		User user = findByUsername(userName);
		if (user == null) {
			return false;
		} else {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
}
