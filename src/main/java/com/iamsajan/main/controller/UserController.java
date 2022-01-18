package com.iamsajan.main.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iamsajan.main.model.User;
import com.iamsajan.main.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@Operation(summary = "Get all users", description = "Get all users from Database.")
	public ResponseEntity<List<User>> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/users/{userId}")
	@Operation(summary = "Get user", description = "Get user by id from Database.")
	public ResponseEntity<User> getUsersById(@PathVariable String userId) {
		return userService.getUsersById(Integer.parseInt(userId));
	}

	@PostMapping("/users")
	@Operation(summary = "Add user", description = "Add user to Database.")
	public ResponseEntity<HttpStatus> addUsers(@RequestBody User user) {
		return userService.addUsers(user);
	}

	@PutMapping("/users/{userId}")
	@Operation(summary = "Update user", description = "Update user by id from Database.")
	public ResponseEntity<HttpStatus> updateUsers(@PathVariable("userId") Integer userId,
			@RequestParam(required = false) String fullName, @RequestParam(required = false) String address,
			@RequestParam(required = false) String email, @RequestParam(required = false) LocalDate dob) {
		return userService.updateUsers(userId, fullName, address, email, dob);
	}

	@DeleteMapping("/users/{userId}")
	@Operation(summary = "Delete user", description = "Delete user by id from Database.")
	public void deleteUsersById(@PathVariable Integer userId) {
		userService.deleteUsersById(userId);
	}

}
