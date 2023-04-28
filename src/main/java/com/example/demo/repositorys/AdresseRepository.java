package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AdressesEntity;

public interface AdresseRepository extends JpaRepository<AdressesEntity, Long> {

}
