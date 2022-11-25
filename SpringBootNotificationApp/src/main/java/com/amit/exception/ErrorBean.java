package com.amit.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorBean {

	private String message; 
	private LocalDateTime localDateTime;
	private String senderServer; 
	private String details; 
}
