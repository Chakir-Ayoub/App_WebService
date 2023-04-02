package com.example.demo.services;


import java.util.List;

import com.example.demo.shared.dto.UserDto;

public interface UserService {
	UserDto createuser(UserDto userDto);
	UserDto GetUserById(String userid);
	UserDto Update(String id,UserDto dto);
	void Delete(String id);
	List<UserDto> getAllUsers(int page,int limit);
}
