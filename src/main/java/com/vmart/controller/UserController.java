package com.vmart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vmart.exception.ResourceNotFoundException;
import com.vmart.model.User;
import com.vmart.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
		try {			
			service.createUser(user);
			return ResponseEntity.ok("User created successfully");
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping("/get-users")
	public ResponseEntity<?> getUsers() {
		try {
			List<User> users = service.getUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PutMapping("/update-user")
	public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
		try {
			service.updateUser(user);
			return ResponseEntity.ok("User updated successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete-user")
	public ResponseEntity<String> deleteUser(@RequestParam Long id) {
		try {
			service.deleteUser(id);
			return ResponseEntity.ok("User deleted successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
