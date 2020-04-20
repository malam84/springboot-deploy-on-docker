package com.springboot.deploy.docker.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 
 * @author Alam_
 *
 */

@Entity
@Data
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String userName;
	
	private String userEmail;
	
	private String mobileNo;
	
	private Date dob;
	

}
