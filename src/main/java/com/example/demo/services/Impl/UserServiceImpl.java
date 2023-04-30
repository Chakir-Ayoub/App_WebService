package com.example.demo.services.Impl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
				user.getAdresses().set(i, address);
		}
		
		user.getContact().setContactId(utils.generateStringId(30));
		user.getContact().setUser(user);
		
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
	public List<UserDto> getAllUsers(int page, int limit,String search,int status) {
		// TODO Auto-generated method stub
		/*if(page>0) page=page-1;
		
		List<UserDto> userDto=new ArrayList<>();
		
		PageRequest pageableRequest = PageRequest.of(page,limit);*/
		List<UserEntity> userEntities;
		if(search.isEmpty()) {
			userEntities=userRepository.findAllUsers();
		}
		else
		{
			 userEntities=userRepository.findAllCriteria(search,status);

		}
		
		//List<UserEntity> userEntities =userpage.getContent();
		List<UserDto> userDto=new ArrayList<>();

		for (UserEntity userEntity : userEntities) {
			
			ModelMapper modelMapper=new ModelMapper();
			//UserDto user=mapper.map(userEntity, Dto.class);
			UserDto Dto=modelMapper.map(userEntity, UserDto.class);
			
			userDto.add(Dto);
			
		}
		return userDto;
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
