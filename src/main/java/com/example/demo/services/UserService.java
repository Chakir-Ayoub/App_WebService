package com.example.demo.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createuser(UserDto userDto);
	UserDto GetUserById(String userid);
	UserDto Update(String id,UserDto dto);
	void Delete(String id);
	List<UserDto> getAllUsers(int page,int limit,String search,int status);
	UserDto getUser(String email);
}
