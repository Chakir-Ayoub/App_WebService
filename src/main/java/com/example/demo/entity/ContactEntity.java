package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ContactEntity implements Serializable {

	private static final long serialVersionUID = 610272075705630445L;

	@Id
	@GeneratedValue
	private long Id;
	@Column(length = 50)
	private String contactId;
	@NotBlank
	private String mobile;
	@NotBlank
	private String skype;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	
}
