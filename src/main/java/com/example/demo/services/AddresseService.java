package com.example.demo.services;

import java.util.List;

import com.example.demo.shared.dto.AddressDto;

public interface AddresseService {
	public List<AddressDto> GetAllAddresses(String email) ;
	public AddressDto createAddress(AddressDto addressDto,String email);
}
