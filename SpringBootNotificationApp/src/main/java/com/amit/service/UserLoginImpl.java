package com.amit.service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amit.exception.UserNotExistException;
import com.amit.models.CurrentUserSession;
import com.amit.models.DetailDto;
import com.amit.models.User;
import com.amit.models.UserDto;
import com.amit.repository.SessionDao;
import com.amit.repository.UserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginImpl implements UserLogin{

	
	@Autowired
	private UserDao udao; 
	
	@Autowired
	private SessionDao sdao; 
	
	
	
	@Override
	public ResponseEntity<DetailDto> loginAccount(UserDto userDto) {
		Optional<User> opt =  udao.findByUserMobile(userDto.getMobile()); 
		
		if(opt.isPresent()) {
			User user = opt.get(); 
			Integer userId = user.getUserId(); 
			
			Optional<CurrentUserSession> currentSessionOpt = sdao.findByUserId(userId); 
			if(currentSessionOpt.isPresent()) {
				DetailDto dt = new DetailDto("You're already logged in.", LocalDateTime.now()); 
				return new ResponseEntity<DetailDto>(dt, HttpStatus.OK); 
			}
			else {
				if(user.getPassword().equals(userDto.getPassword())) {
					String key = RandomString.make(6);
					CurrentUserSession cus = new CurrentUserSession(userId, key, LocalDateTime.now()); 
					sdao.save(cus);
					DetailDto dt = new DetailDto("You've been successful logged-in", LocalDateTime.now());
					return new ResponseEntity<DetailDto>(dt, HttpStatus.ACCEPTED);  
				}
				else {
					throw new RuntimeException("Please provide a valid password");
				}
		
			}
		}
		else {
			throw new UserNotExistException("Mo user found with the provided mobile number : " + userDto.getMobile() + ". \nKindly register first"); 
		}
	}
	
	
	
	@Override
	public ResponseEntity<DetailDto> logOut(String uuid) {		
		Optional<CurrentUserSession>  cusOpt = sdao.findByUuid(uuid); 
		if(cusOpt.isPresent()) {
			CurrentUserSession cus = cusOpt.get(); 
			sdao.delete(cus);
			DetailDto dt = new DetailDto("User logged out sucessfully", LocalDateTime.now()); 
			return new ResponseEntity<DetailDto>(dt, HttpStatus.OK);  
		}
		else {
			throw new RuntimeException("Invalid uuid");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
