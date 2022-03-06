package com.vmart.service;

import java.util.List;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.User;

public interface UserService {
	void createUser(User user);
	List<User> getUsers();
	void updateUser(User user) throws ResourceNotFoundException;
	void deleteUser(Long id) throws ResourceNotFoundException;
	User getUserById(Long id) throws ResourceNotFoundException;
}
