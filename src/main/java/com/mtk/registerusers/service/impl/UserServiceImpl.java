package com.mtk.registerusers.service.impl;

import com.mtk.registerusers.dto.UserDTO;
import com.mtk.registerusers.entities.User;
import com.mtk.registerusers.repository.UserRepository;
import com.mtk.registerusers.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService
{
	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public void create(UserDTO dto)
	{
		assertEmailNotDuplicate(dto.email());

		User user = new User();
		user.setName(dto.name());
		user.setAge(dto.age());
		user.setEmail(dto.email());

		repository.save(user);
	}

	@Override
	public void remove(Long id)
	{
		repository.deleteById(id);
	}

	@Override
	public List<UserDTO> findAll()
	{
		return repository.findAll().stream()
			.map(user -> new UserDTO(user.getId(), user.getName(), user.getAge(), user.getEmail()))
			.collect(Collectors.toList());
	}

	private void assertEmailNotDuplicate(String email)
	{
		if (email == null || email.isEmpty())
		{
			return;
		}

		User user = repository.findUserByEmail(email);

		if (user != null)
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail already exist!");
		}
	}
}
