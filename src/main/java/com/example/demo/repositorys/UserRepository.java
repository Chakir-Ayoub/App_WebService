package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByemail(String email);
	UserEntity findByuserId(String userId);
	UserEntity findByfirstName(String name);
}
