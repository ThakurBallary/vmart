package com.vmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.User;
import com.vmart.repository.UserRepository;
import com.vmart.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public void createUser(User user) {
		repository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return repository.findAll();
	}

	@Override
	public void updateUser(User user) throws ResourceNotFoundException {
		User existingUser = repository.findById(user.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Could not update. User not found with id: " + user.getId()));
		existingUser.setName(user.getName());
		existingUser.setRole(user.getRole());
		repository.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) throws ResourceNotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Could not delete. User not found with id: " + id);
		}
	}

	@Override
	public User getUserById(Long id) throws ResourceNotFoundException {
		User user = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return user;
	}

}
