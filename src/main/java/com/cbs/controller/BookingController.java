package com.cbs.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.service.IBookingService;

@RestController
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	// get all users booking in given time
	@GetMapping("/bookings/users")
	public ResponseEntity<Map<String, Object>> getAllUsersBooking(@RequestParam String email,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingFromDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingToDate,
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int itemsPerPage) {

		return ResponseEntity.status(HttpStatus.OK).body(
				bookingService.getAllUsersBooking(email, bookingFromDate, bookingToDate, pageNumber, itemsPerPage));

	}

	// get all card booking in given time
	@GetMapping("/bookings/cars")
	public ResponseEntity<Map<String, Object>> getAllCarsBooking(@RequestParam int id,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingFromDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingToDate,
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int itemsPerPage) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(bookingService.getAllCarBooking(id, bookingFromDate, bookingToDate, pageNumber, itemsPerPage));
	}

	// get all cars which has valid insurance in given specified time
	@GetMapping("/cars/validinsurances")
	public ResponseEntity<Map<String, Object>> getAllCarWithValidInsurance(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime insuranceTill,
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int itemsPerPage) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(bookingService.getAllCarWithValidInsurance(insuranceTill, pageNumber, itemsPerPage));
	}
	
}