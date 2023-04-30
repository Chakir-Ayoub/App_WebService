package com.example.demo.repositorys;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByemail(String email);
	UserEntity findByuserId(String userId);
	UserEntity findByfirstName(String name);
	
	/*@Query(value = "select * from user u where u.first_name='Walid' ",nativeQuery = true)
	Page<UserEntity> findAllUserByfirstName(Pageable pagebelRequest);*/

	@Query(value = "SELECT * FROM user ",nativeQuery = true)
	List<UserEntity> findAllUsers( );
	
	//Methode 1
	/*@Query(value = "SELECT * FROM user u where (u.first_name=?1 OR u.last_name=?1) AND u.email_verification_status=?2 ",nativeQuery = true)
	List<UserEntity> findAllCriteria(String search ,int status);*/
	
	
	@Query(value = "SELECT * FROM user u where (u.first_name= :search OR u.last_name= :search) AND u.email_verification_status= :status ",nativeQuery = true)
	List<UserEntity> findAllCriteria(@Param("search") String search ,@Param("status") int status);
}
