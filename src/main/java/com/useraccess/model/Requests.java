package com.useraccess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requests {
	
	 
	private int id;
	    private int userId;  
	    private int softwareId; 
	    private String accessType;
	    private String reason;
	    private String status;


	  
	  
	// TODO Auto-generated constructor stub
}