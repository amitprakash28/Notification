package com.amit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.models.CurrentUserSession;

public interface SessionDao extends JpaRepository<CurrentUserSession, Integer>{

	public Optional<CurrentUserSession> findByUserId(Integer uid); 
	
	public Optional<CurrentUserSession> findByUuid(String key);
}
