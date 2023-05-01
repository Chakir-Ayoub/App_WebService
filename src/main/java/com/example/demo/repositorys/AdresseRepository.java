package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AdressesEntity;
import com.example.demo.entity.UserEntity;

@Repository
public interface AdresseRepository extends CrudRepository<AdressesEntity, Long> {
	List<AdressesEntity> findByUser(UserEntity currentUser);
}
