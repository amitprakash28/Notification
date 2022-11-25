package com.amit.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId; 
	
	@NotNull(message = "please provide your name")
	private String userName;
	
	@NotNull(message = "please provide your email")
	//@Column(unique = true)
	private String userEmail;
	
	@NotNull(message = "please provide your mobile")
	@Pattern(regexp = "[789]{1}[0-9]{9}")
	@Column(unique = true)
	private String userMobile;
	
	@NotNull(message = "please provide your address")
	private String address; 
	
	@NotNull(message = "password can't be empty")
	@Size(min = 4, message = "Password must be at least 4 characters long")
	private String password; 
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Notification> messages = new ArrayList<>(); 
}











