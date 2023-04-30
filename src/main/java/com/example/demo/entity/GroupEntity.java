package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.*;

@Entity
public class GroupEntity implements Serializable {

	private static final long serialVersionUID = 2785586112041064653L;
	
	@Id
	@GeneratedValue
	private long Id;
	@Column(name = "name",length = 30)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "groups_user",joinColumns = {@JoinColumn(name="goups_id")},inverseJoinColumns = {@JoinColumn(name="users_id")})
	private Set<UserEntity> userEntities=new HashSet<>();
}
