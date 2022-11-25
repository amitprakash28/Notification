package com.amit.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer msgId; 
	private String receiverMob;
	private String receiverEmail; 
	
	@NotNull
	private Integer receiverId; 
	
	@NotNull
	private String message; 
	
	private LocalDateTime localDateTime = LocalDateTime.now(); 
	
	@NotNull
	private String type; 
}
