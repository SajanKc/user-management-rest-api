package com.iamsajan.main.controller;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iamsajan.main.model.User;
import com.iamsajan.main.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users from Database.")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user", description = "Get user by id from Database.")
    public ResponseEntity<User> getUsersById(@PathVariable String userId) {
        return userService.getUsersById(Integer.parseInt(userId));
    }

    @GetMapping("/account-number/{accountNumber}")
    public ResponseEntity<User> getUserByAccountNumber(@PathVariable String accountNumber) {
        return userService.getUserByAccountNumber(accountNumber);
    }

    @PostMapping
    @Operation(summary = "Add user", description = "Add user to Database.")
    public ResponseEntity<HttpStatus> addUsers(@RequestBody User user) {
        return userService.addUsers(user);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Update user by id from Database.")
    public ResponseEntity<HttpStatus> updateUsers(@PathVariable("userId") Integer userId,
                                                  @RequestParam(required = false) String fullName, @RequestParam(required = false) String address,
                                                  @RequestParam(required = false) String email, @RequestParam(required = false) LocalDate dob) {
        return userService.updateUsers(userId, fullName, address, email, dob);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Delete user by id from Database.")
    public void deleteUsersById(@PathVariable Integer userId) {
        userService.deleteUsersById(userId);
    }

}
