package com.amit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amit.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	public Optional<User> findByUserId(Integer userId); 
	public Optional<User> findByUserMobile(String mobile); 

}
