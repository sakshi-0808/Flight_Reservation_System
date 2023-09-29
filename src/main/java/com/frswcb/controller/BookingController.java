package com.frswcb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frswcb.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/bookFlight/{flightId}/{passId}")
	public ResponseEntity <String> bookFlight(@PathVariable("flightId")int flightId, @RequestParam(required = false, defaultValue= "1") int noOfPass,
			@PathVariable("passId") int passId)
	{
		if(bookingService.bookFlight(flightId, noOfPass,passId))
		{
			return new ResponseEntity<String>("Booking confirmed", HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<String>( "failed to book flight",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/cancelFlight/{bookingId}")
	public ResponseEntity <String> cancelFlight(@PathVariable("bookingId")int bookingId)
	{
		bookingService.cancelFlight(bookingId);
		
		return new ResponseEntity<>("Booking has been cancelled!", HttpStatus.OK);
		
	}
}
