package com.frswcb.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.frswcb.entity.Booking;



public class CancelDto {
	
private int CancelId;
	
	@Size(max = 50 , message = "max limit 50 characters")
	@Size(min = 12 , message = "min 3 char required")
	@NotNull(message= "Status is required")
	private String status;
	
	@NotNull(message= "noOfPass is required")
	private int noOfPass;
	
	
	@ManyToOne //one flight can take many booking
	private Booking booking;

}
