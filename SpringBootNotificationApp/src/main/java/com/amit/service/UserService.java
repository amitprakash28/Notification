package com.amit.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.amit.models.DetailDto;
import com.amit.models.Notification;
import com.amit.models.User;

public interface UserService {
	
	public User saveUser(User user);
	public ResponseEntity<DetailDto> sendMessage(Notification message, String uuid);
	public ResponseEntity<List<Notification>> getAllMessages(String uuid); 
}
