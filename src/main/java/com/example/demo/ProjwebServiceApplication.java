package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.UserEntity;
import com.example.demo.repositorys.UserRepository;

@SpringBootApplication
public class ProjwebServiceApplication implements CommandLineRunner {
	@Autowired
	UserRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ProjwebServiceApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	}
}
