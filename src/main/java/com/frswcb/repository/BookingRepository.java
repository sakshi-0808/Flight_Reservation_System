package com.frswcb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frswcb.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("select max(confirmationId) from Booking")
	String getLastAddedConfirmationId();
}
