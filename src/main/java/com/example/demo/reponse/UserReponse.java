package com.example.demo.reponse;

import java.util.List;

public class UserReponse {
	private String UserId;
	private String firstName;
	private String lastName;
	private String email;
	private List<AdresseRespnse> adresses;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AdresseRespnse> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AdresseRespnse> adresses) {
		this.adresses = adresses;
	}
	
	
}
