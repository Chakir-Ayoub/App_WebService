package com.example.demo.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repositorys.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.AddressDto;
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
		
		for(int i=0;i<user.getAdresses().size();i++) {
				AddressDto address= user.getAdresses().get(i);
				address.setUser(user);
				address.setAddressId(utils.generateStringId(30));
				//address.setAdresseId(utils.generateStringId(32));
				user.getAdresses().set(i, address);
		}
		
		ModelMapper modelMapper=new ModelMapper();
		UserEntity userEntity =modelMapper.map(user, UserEntity.class);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.generateStringId(32));
		
		UserEntity newUser= userRepository.save(userEntity);
		
		UserDto userDto=modelMapper.map(newUser, UserDto.class);
		
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
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity=userRepository.findByemail(email);
		
		if(userEntity==null) throw new RuntimeException(email);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
	}
	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		UserEntity userEntity=userRepository.findByemail(email);
		if(userEntity==null) throw new RuntimeException("User Not Exist");

		UserDto dto=new UserDto();
		BeanUtils.copyProperties(userEntity, dto);
		return dto;
	}
	

}
