package com.example.demo.shared.dto;


public class AddressDto {
	private Long id;
	private String adresseId;
	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;
	private UserDto user;
	
	
	public String getAdresseId() {
		return adresseId;
	}
	public void setAdresseId(String adresseId) {
		this.adresseId = adresseId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
