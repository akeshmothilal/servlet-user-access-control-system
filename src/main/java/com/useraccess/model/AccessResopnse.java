package com.useraccess.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessResopnse {
	
	
	
	    private int id;
	    private String employeeName;
	    private String softwareName;
	    private String accessType;
	    private String reason;

}
