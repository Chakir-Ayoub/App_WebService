package com.example.demo.controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.reponse.AdresseRespnse;
import com.example.demo.request.AdresseRequest;
import com.example.demo.services.AddresseService;
import com.example.demo.shared.dto.AddressDto;

@RestController()
@RequestMapping("/adresses")
public class AddressController {
	
	@Autowired
	AddresseService addresseService;
	
	@GetMapping
	public ResponseEntity<List<AdresseRespnse>> GetAllAdresses(Principal principal){
		List<AddressDto> addressDtos=addresseService.GetAllAddresses(principal.getName());
		
		Type AdresseRespnse= new TypeToken<List<AdresseRespnse>>() {}.getType();
		List<AdresseRespnse> adresseRespnses=new ModelMapper().map(addressDtos, AdresseRespnse);
		
		return new ResponseEntity<>(adresseRespnses,HttpStatus.OK);
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<AdresseRespnse> StoreAddresse(@RequestBody AdresseRequest adresseRequest,Principal principal){
		ModelMapper modelMapper=new ModelMapper();
		
		AddressDto addressDto=modelMapper.map(adresseRequest, AddressDto.class);
		AddressDto createAddress =addresseService.createAddress(addressDto,principal.getName());
		
		AdresseRespnse newAddress=modelMapper.map(createAddress, AdresseRespnse.class);
		
		return new ResponseEntity<AdresseRespnse>(newAddress,HttpStatus.CREATED);
	}
}
