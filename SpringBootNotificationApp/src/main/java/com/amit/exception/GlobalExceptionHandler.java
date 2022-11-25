package com.amit.exception;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorBean> throwLoginException(LoginException le, WebRequest request){
		ErrorBean er = new ErrorBean(); 
		er.setMessage(le.getMessage());
		er.setSenderServer("Twillio-SB Web Server");
		er.setDetails(request.getDescription(false));
		er.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorBean>(er, HttpStatus.BAD_REQUEST); 
	}
	
	
	
	@ExceptionHandler(UserNotRegisterException.class)
	public ResponseEntity<ErrorBean> throwUserNotRegisterException(UserNotRegisterException le, WebRequest request){
		ErrorBean er = new ErrorBean(); 
		er.setMessage(le.getMessage());
		er.setSenderServer("Twillio-SB Web Server");
		er.setDetails(request.getDescription(false));
		er.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorBean>(er, HttpStatus.BAD_REQUEST); 
	}
	

	
	
	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<ErrorBean> throwUserNotExistException(UserNotExistException le, WebRequest request){
		ErrorBean er = new ErrorBean(); 
		er.setMessage(le.getMessage());
		er.setSenderServer("Twillio-SB Web Server");
		er.setDetails(request.getDescription(false));
		er.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorBean>(er, HttpStatus.BAD_REQUEST); 
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorBean> showIvalideError(MethodArgumentNotValidException me, WebRequest request){
		ErrorBean er = new ErrorBean(); 
		er.setSenderServer("Twillio-SB Web Server"); 
		er.setLocalDateTime(LocalDateTime.now()); 
		er.setDetails(request.getDescription(false));
		er.setMessage(me.getBindingResult().getFieldError().getDefaultMessage()); 
		return new ResponseEntity<ErrorBean>(er, HttpStatus.BAD_REQUEST); 
	}
	
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorBean> throwGenericExeption(RuntimeException le, WebRequest request){
		ErrorBean er = new ErrorBean(); 
		er.setMessage(le.getMessage());
		er.setSenderServer("Twillio-SB Web Server");
		er.setDetails(request.getDescription(false));
		er.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<ErrorBean>(er, HttpStatus.BAD_REQUEST); 
	}
	
}
