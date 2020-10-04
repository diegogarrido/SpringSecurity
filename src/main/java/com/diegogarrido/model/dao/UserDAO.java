package com.diegogarrido.model.dao;

import java.util.Collection;
import java.util.Optional;

import com.diegogarrido.util.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diegogarrido.model.User;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Iterable<User> findByRoleIn(Collection<Role> roles);
}
