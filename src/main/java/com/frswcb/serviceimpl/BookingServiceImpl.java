package com.frswcb.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frswcb.dto.BookingDto;
import com.frswcb.dto.FlightDto;
import com.frswcb.entity.Booking;
import com.frswcb.entity.Flight;
import com.frswcb.entity.Passenger;
import com.frswcb.exceptions.ResourceNotFoundException;
import com.frswcb.repository.BookingRepository;
import com.frswcb.repository.FlightRepository;
import com.frswcb.repository.PassengerRepository;
import com.frswcb.service.BookingService;

import com.frswcb.util.BookingConverter;
@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingConverter bConverter;
	@Autowired
	BookingRepository bRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passRepository;
	
	@Override
	public Boolean bookFlight(int flightId, int noOfPass, int passId) {
		Optional <Flight> opFlight = flightRepository.findById(flightId);
		Passenger pass = passRepository.findById(passId).orElseThrow(()->
		new ResourceNotFoundException("Passenger", "Id", passId));
		
		Booking booking = new Booking();
		
		if(opFlight!= null)
		{
			Flight flight = opFlight.get();
			
			if(flight.getNoOfSeat()>= noOfPass)
			{
				flight.setNoOfSeat(flight.getNoOfSeat()-noOfPass);
				double totalPrice = noOfPass * flight.getFare();
				
				flightRepository.save(flight);
				
				String lastAdded = bRepository.getLastAddedConfirmationId();
				
				if(lastAdded == null)
				{
					lastAdded = "CON0";
				}
				
				String prefix = lastAdded.substring(0,3);
				int postfix = Integer.parseInt(lastAdded.substring(3)) + 1;
				lastAdded = prefix + postfix;
				booking.setFlight(flight);
				booking.setConfirmationId(lastAdded);
				booking.setNoOfPass(noOfPass);
				booking.setStatus("BOOKED");
				booking.setTotalPrice(totalPrice);
				booking.setPassenger(pass);
				bRepository.save(booking);
				return true;
			}
		}
		return false;
	}

	@Override
	public void cancelFlight(int bookingId) {
		Booking book = bRepository.findById(bookingId).orElseThrow(()->
		new ResourceNotFoundException("Booking", "Id", bookingId));
		
		book.setStatus("CANCELLED");
		Flight flight = book.getFlight();
		
		flight.setNoOfSeat(flight.getNoOfSeat()+book.getNoOfPass());
		
		flightRepository.save(flight);
		bRepository.save(book);
		
	}
	
}
