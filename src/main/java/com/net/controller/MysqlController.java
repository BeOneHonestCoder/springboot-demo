package com.net.controller;

import com.net.domain.User;
import com.net.service.MysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mysql/users")
public class MysqlController {

	@Autowired
	private MysqlService service;

	@GetMapping
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return service.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		service.deleteById(id);
	}

}
