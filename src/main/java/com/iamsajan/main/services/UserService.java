package com.iamsajan.main.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iamsajan.main.model.User;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@Service
public interface UserService {

	public ResponseEntity<List<User>> getUsers();

	public ResponseEntity<User> getUsersById(Integer userId);

	public ResponseEntity<HttpStatus> deleteUsersById(Integer userId);

	public ResponseEntity<HttpStatus> addUsers(User user);

	public ResponseEntity<HttpStatus> updateUsers(Integer userId, String fullName, String address, String email,
			LocalDate dob);

    ResponseEntity<User> getUserByAccountNumber(String accountNumber);
}
