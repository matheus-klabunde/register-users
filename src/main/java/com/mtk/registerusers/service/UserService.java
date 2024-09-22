package com.mtk.registerusers.service;

import com.mtk.registerusers.dto.UserDTO;
import java.util.List;

public interface UserService
{
	void create(UserDTO user);

	void remove(Long id);

	List<UserDTO> findAll();
}
