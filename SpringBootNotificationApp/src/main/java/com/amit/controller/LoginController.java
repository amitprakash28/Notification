package com.amit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amit.models.DetailDto;
import com.amit.models.UserDto;
import com.amit.service.UserLogin;

@RestController
public class LoginController {

	@Autowired
	private UserLogin ulogin; 
	
	
	@PostMapping("/login")
	public ResponseEntity<DetailDto> userLoginHandler(@RequestBody UserDto userDto) {
		return ulogin.loginAccount(userDto); 
	}
	
	
	@DeleteMapping("/logout/{uuid}")
	public ResponseEntity<DetailDto> userLogoutHandler(@PathVariable String uuid){
		return ulogin.logOut(uuid);
	}
	
}
