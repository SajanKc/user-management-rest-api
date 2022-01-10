package com.iamsajan.main.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iamsajan.main.model.User;
import com.iamsajan.main.repository.UserRepository;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<List<User>> getUsers() {
		try {
			List<User> users = userRepository.findAll();
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(users, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<User> getUsersById(Integer userId) {
		try {
			Optional<User> user = userRepository.findById(userId);
			if (user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<HttpStatus> deleteUsersById(Integer userId) {
		boolean exists = userRepository.existsById(userId);
		if (!exists) {
			throw new IllegalStateException("User with id " + userId + " does not exists.");
		} else {
			try {
				userRepository.deleteById(userId);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public ResponseEntity<HttpStatus> addUsers(User user) {
		Optional<User> userIdOptional = userRepository.findById(user.getUserId());
		Optional<User> userEmailOptional = userRepository.findUserByEmail(user.getEmail());
		if (userIdOptional.isPresent()) {
			throw new IllegalStateException("User with Id: " + user.getUserId() + " already exists.");
		} else if (userEmailOptional.isPresent()) {
			throw new IllegalStateException("User with email: " + user.getEmail() + " already exists.");
		} else {
			try {
				userRepository.save(user);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	@Transactional
	public ResponseEntity<HttpStatus> updateUsers(Integer userId, String fullName, String address, String email,
			LocalDate dob) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (fullName != null && fullName.length() > 3 && !Objects.equals(user.getFullName(), fullName)) {
				user.setFullName(fullName);
			}

			if (address != null && address.length() > 0 && !Objects.equals(user.getAddress(), address)) {
				user.setAddress(address);
			}

			if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
				Optional<User> userEmail = userRepository.findUserByEmail(email);
				if (userEmail.isPresent()) {
					throw new IllegalStateException("Email is already taken.");
				}
				user.setEmail(email);
			}

			if (dob != null && !Objects.equals(user.getDob(), dob)) {
				user.setDob(dob);
			}

			userRepository.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}