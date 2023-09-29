package com.frswcb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frswcb.entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

	Airline findByAirlineName(String airName);
}
