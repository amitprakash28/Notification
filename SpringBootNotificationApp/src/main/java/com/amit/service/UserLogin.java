package com.amit.service;
import org.springframework.http.ResponseEntity;

import com.amit.models.DetailDto;
import com.amit.models.UserDto;

public interface UserLogin {

	public ResponseEntity<DetailDto> loginAccount(UserDto userDto); 
	public ResponseEntity<DetailDto> logOut(String uuid); 
}
