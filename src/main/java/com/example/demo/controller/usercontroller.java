package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.UserException;
import com.example.demo.reponse.ErrorMessages;
import com.example.demo.reponse.UserReponse;
import com.example.demo.request.UserRequest;
import com.example.demo.services.UserService;
import com.example.demo.shared.dto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class usercontroller {
	@Autowired
	UserService userService;
	
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserReponse> Get(@PathVariable String id) {
		UserDto user=userService.GetUserById(id);
		UserReponse reponse=new UserReponse();
		BeanUtils.copyProperties(user, reponse);
		return new ResponseEntity<UserReponse>(reponse,HttpStatus.ACCEPTED);
	}
	
	public List<UserReponse> GetAllUsers(@RequestParam(value = "page") int page,@RequestParam(value = "limit") int limit){
		List<UserReponse> userReponses=new ArrayList<>();
		
		List<UserDto> users=userService.getAllUsers(page,limit);
		
		for(UserDto userdto: users) {
			UserReponse user=new UserReponse();
			BeanUtils.copyProperties(userdto, user);
			
			userReponses.add(user);
		}
		return userReponses;
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserReponse> save(@RequestBody @Valid UserRequest userRequest) throws Exception {
		if (userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		//UserDto userdo=new UserDto();
		//BeanUtils.copyProperties(userRequest, userdo);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userdo= modelMapper.map(userRequest, UserDto.class);
		//Couche service
		UserDto createuser= userService.createuser(userdo);
		UserReponse userReponse=modelMapper.map(createuser, UserReponse.class);

		
		return new ResponseEntity<UserReponse>(userReponse,HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/{id}",
			consumes = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserReponse> Update(@PathVariable String id,@RequestBody UserRequest request) throws Exception { 
	
		UserDto dto=new UserDto();
		
		BeanUtils.copyProperties(request, dto);
		
		UserDto dto2=userService.Update(id, dto);
		UserReponse reponse=new UserReponse();
		
		BeanUtils.copyProperties(dto2, reponse);
		
		return new ResponseEntity<UserReponse>(reponse,HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> Delete(@PathVariable String id) {
		
		userService.Delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
