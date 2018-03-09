package org.james.demo.controller;

import java.util.Objects;

import org.james.demo.exception.ResourceNotFoundException;
import org.james.demo.model.User;
import org.james.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ExceptionHandler(ResourceNotFoundException.class)
	public void notFound() {
	}

	@GetMapping("")
	public Flux<User> list() {
		return userService.list();
	}

	@GetMapping("/{id}")
	public Mono<User> getById(@PathVariable String id) {
		return userService.getById(id);
	}

	@PostMapping("")
	public Flux<User> create(@RequestBody Flux<User> users) {
		return userService.createOrUpdate(users);
	}

	@PutMapping("/{id}")
	public Mono<User> update(@PathVariable String id, @RequestBody User user) {
		Objects.requireNonNull(user);
		user.setUserId(id);

		return userService.createOrUpdate(user);
	}

	@DeleteMapping("/{id}")
	public Mono<User> delete(@PathVariable String id) {
		return userService.delete(id);
	}
}
