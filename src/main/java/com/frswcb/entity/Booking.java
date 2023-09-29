package com.frswcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@Column( nullable = false)
	private String confirmationId;
	
	@Column( nullable = false)
	private String status;
	
	@Column(nullable = false)
	private int noOfPass;
	
	@Column(precision = 2, nullable = false)
	private double totalPrice;
	
	@ManyToOne //one flight can take many booking
	private Flight flight;
	
	@OneToOne
	private Passenger passenger;


}
