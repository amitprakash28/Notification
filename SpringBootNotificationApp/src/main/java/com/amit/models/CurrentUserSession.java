package com.amit.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId; 
	
	private Integer userId; 
	
	private String uuid; 
	
	private LocalDateTime local; 
	
	public CurrentUserSession(Integer userId, String uuid, LocalDateTime local) {
		super(); 
		this.userId = userId;
		this.uuid = uuid;
		this.local = local;
	}
	
}
