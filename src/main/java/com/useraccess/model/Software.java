package com.useraccess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Software {
	
	 private int id;
	    private String name;
	    private String description;
	    private String accessLevels;

}
