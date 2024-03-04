package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eam.demo.models.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	User findByUserEmail(String userEmail)	;

}
