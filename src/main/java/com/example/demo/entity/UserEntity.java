package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class UserEntity implements Serializable {


	private static final long serialVersionUID = 999861692305194974L;
	
	@Id
	@GeneratedValue()
	private Long Id;
	
	@Column(nullable = false)
	private String userId;
	@Column(nullable = true,length = 50)
	private String firstName;
	@Column(nullable = true,length = 50)
	private String lastName;
	@Column(nullable = false,length = 120,unique = true)
	private String email;
	@Column(nullable = false)
	private String encryptedPassword;
	@Column(nullable = true)
	private String emailVerificationToken;
	//@Column(columnDefinition = "boolean defult false")
	@Column(nullable = false)
	private Boolean emailVerificationStatus=false;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<AdressesEntity> addresess;
	public Long getId() {
		return Id;
	}
	public List<AdressesEntity> getAddresess() {
		return addresess;
	}
	public void setAddresess(List<AdressesEntity> addresess) {
		this.addresess = addresess;
	}
	public void setId(Long id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public UserEntity(String userId, String firstName, String lastName, String email, String encryptedPassword,
			String emailVerificationToken, Boolean emailVerificationStatus) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.emailVerificationToken = emailVerificationToken;
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
