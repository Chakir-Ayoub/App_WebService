package com.example.demo.entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Adresse")
public class AdressesEntity implements Serializable  {
	private static final long serialVersionUID = -7279229704597337526L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 30,nullable = false)
	private String addressId;
	@Column(length = 20,nullable = false)
	private String city;
	@Column(length = 20,nullable = false)
	private String country;
	@Column(length = 50,nullable = false)
	private String street;
	@Column(length = 7,nullable = false)
	private String postal;
	@Column(length = 20,nullable = false)
	private String type;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}

	
	
}
