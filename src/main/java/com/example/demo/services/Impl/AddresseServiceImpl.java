package com.example.demo.services.Impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdressesEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repositorys.AdresseRepository;
import com.example.demo.repositorys.UserRepository;
import com.example.demo.services.AddresseService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.AddressDto;
import com.example.demo.shared.dto.UserDto;

@Service
public class AddresseServiceImpl implements AddresseService {

	@Autowired
	AdresseRepository adresseRepository;
	@Autowired 
	UserRepository userRepository;
	@Autowired
	Utils utils;
	
	@Override
	public List<AddressDto> GetAllAddresses(String email) {
		// TODO Auto-generated method stub
		UserEntity currentUser=userRepository.findByemail(email);
		
		List<AdressesEntity> adressesEntities=currentUser.getAdmin()==true? (List<AdressesEntity>) adresseRepository.findAll() : adresseRepository.findByUser(currentUser);
		
		
		Type listeType= new TypeToken<List<AddressDto>>() {}.getType();
		List<AddressDto> addressDtos=new ModelMapper().map(adressesEntities, listeType);
		
		return addressDtos;
	}
	@Override
	public AddressDto createAddress(AddressDto addressDto, String email) {
		// TODO Auto-generated method stub
		UserEntity currentUser=userRepository.findByemail(email);
		
		ModelMapper modelMapper=new ModelMapper();
		UserDto userDto=modelMapper.map(currentUser, UserDto.class);
		
		addressDto.setAddressId(utils.generateStringId(30));
		addressDto.setUser(userDto);
		
		AdressesEntity adressesEntity=modelMapper.map(addressDto, AdressesEntity.class);
		
		AdressesEntity newAddress=adresseRepository.save(adressesEntity);
		
		AddressDto addressDto2=modelMapper.map(newAddress, AddressDto.class);
		
		return addressDto2;
	}

}
