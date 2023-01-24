package com.attornatus.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.attornatus.usermanagement.entities.Address;
import com.attornatus.usermanagement.entities.User;
import com.attornatus.usermanagement.service.UserService;
import com.attornatus.usermanagement.service.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok().body(userService.findAllUsers());
	}

	@GetMapping("{userId}")
	public ResponseEntity<?> findUserById(@PathVariable Long userId) {

		try {
			User user = userService.findUserById(userId);
			return ResponseEntity.ok(user);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
	}

	@PutMapping("{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User newUser) {

		try {
			User user = userService.updateUser(userId, newUser);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/address/{userId}")
	public ResponseEntity<?> saveAddress(@PathVariable Long userId, @RequestBody Address address) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAddress(userId, address));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/address/{userId}")
	public ResponseEntity<?> findAddresses(@PathVariable Long userId) {

		try {
			List<Address> addresses = userService.findAddresses(userId);
			return ResponseEntity.ok(addresses);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PatchMapping("address/{userId}")
	public ResponseEntity<?> activateMainAddress(@PathVariable Long addressId) {

		try {
			Address address = userService.activateMainAddress(addressId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(address);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
