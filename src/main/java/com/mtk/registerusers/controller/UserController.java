package com.mtk.registerusers.controller;

import com.mtk.registerusers.dto.UserDTO;
import com.mtk.registerusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
	private final UserService service;

	@Autowired
	public UserController(UserService service)
	{
		this.service = service;
	}

	@PostMapping("/user")
	public ResponseEntity<String> register(@RequestBody UserDTO dto)
	{
		service.create(dto);
		return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id)
	{
		service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> findAll()
	{
		List<UserDTO> users = service.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
