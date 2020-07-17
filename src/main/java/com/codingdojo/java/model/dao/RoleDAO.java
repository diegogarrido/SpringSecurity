package com.codingdojo.java.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.java.model.Role;

@Repository
@Transactional
public interface RoleDAO extends CrudRepository<Role, Integer> {
	
	Optional<Role> findByName(String name);
	
}
