package com.amit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.models.DetailDto;
import com.amit.models.Notification;
import com.amit.models.User;
import com.amit.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService uService; 
	
	
	@PostMapping("/save")
	public User saveUserHandler(@RequestBody @Valid User user) {
		return uService.saveUser(user); 
	}
	
	
	@GetMapping("/send/{uuid}")
	public ResponseEntity<DetailDto> sendMessageHandler(@RequestBody Notification notification, @PathVariable String uuid) {
		return uService.sendMessage(notification, uuid);
	}
	
	
	@GetMapping("/notifications/{uuid}")
	public ResponseEntity<List<Notification>> getAllNotificationsHandler(@PathVariable String uuid){
		return uService.getAllMessages(uuid); 
	}
}
