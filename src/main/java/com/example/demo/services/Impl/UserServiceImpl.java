package com.example.demo.services.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repositorys.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	//BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDto createuser(UserDto user) {
		// TODO Auto-generated method stub
		UserEntity usercheck=userRepository.findByemail(user.getEmail());
		if(usercheck!=null) throw new RuntimeException("User Already Exist");
		
		UserEntity userEntity =new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));
		
		UserEntity newUser= userRepository.save(userEntity);
		UserDto userDto=new UserDto(); 
		
		BeanUtils.copyProperties(newUser, userDto);
		
		return userDto;
	}
	@Override
	public UserDto GetUserById(String userid) {
		// TODO Auto-generated method stub
	
		UserEntity entity=userRepository.findByuserId(userid);
		if(entity==null) throw new RuntimeException("User Not Exist");
		UserDto dto=new UserDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	@Override
	public UserDto Update(String id, UserDto dto) {
		// TODO Auto-generated method stub
		UserEntity entity=userRepository.findByuserId(id);
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		
		UserEntity userupdate=userRepository.save(entity);
		UserDto dto2=new UserDto();
		BeanUtils.copyProperties(userupdate, dto2);
		return dto2;
	}
	@Override
	public void Delete(String id) {
		// TODO Auto-generated method stub
		UserEntity user=userRepository.findByuserId(id);
		
		if(user==null) throw new RuntimeException("User Not exist");
		userRepository.delete(user);
		
	}
	@Override
	public List<UserDto> getAllUsers(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
