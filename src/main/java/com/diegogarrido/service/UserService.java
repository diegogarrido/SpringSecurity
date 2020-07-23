package com.diegogarrido.service;

import java.util.Date;
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
			user = addRole(user, Role.SUPER_ADMIN);
		}else{
			user = addRole(user, Role.USER);
		}
		return uDao.save(user);
	}

	public User setLastSignIn(User user) {
		user.setLastSignIn(new Date());
		return uDao.save(user);
	}

	public User addRole(User user, Role role) {
		if (!user.getRoles().contains(role)) {
			user.getRoles().clear();
			user.getRoles().add(role);
			return uDao.save(user);
		} else {
			return user;
		}
	}

	public User removeRole(User user, Role role) {
		if (user.getRoles().contains(role)) {
			user.getRoles().remove(role);
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

	public boolean userExist(Integer id) {
		return uDao.findById(id).isPresent();
	}

	public boolean emailExists(String email) {
		return uDao.findByEmail(email).isPresent();
	}

	public boolean usernameExists(String userName) {
		return uDao.findByUsername(userName).isPresent();
	}

	public Iterable<User> findAll() {
		return uDao.findAll();
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
