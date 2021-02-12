package com.cbs.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.entity.Booking;
import com.cbs.service.IBookingService;

@RestController
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	// get all users booking in given time
	@GetMapping("/users/bookings")
	public ResponseEntity<Map<String, Object>> getAllUsersBooking(@RequestParam String email,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingFromDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingToDate,
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int itemsPerPage) {

		return ResponseEntity.status(HttpStatus.OK).body(
				bookingService.getAllUsersBooking(email, bookingFromDate, bookingToDate, pageNumber, itemsPerPage));

	}

	// get all card booking in given time
	@GetMapping("/cars/bookings")
	public ResponseEntity<Map<String, Object>> getAllCarsBooking(@RequestParam int id,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingFromDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime bookingToDate,
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int itemsPerPage) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(bookingService.getAllCarBooking(id, bookingFromDate, bookingToDate, pageNumber, itemsPerPage));
	}

	// new booking
	@PostMapping(value = "/bookings")
	public ResponseEntity<Booking> addNewBooking(@RequestBody Booking newBooking) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.addNewBooking(newBooking));
	}

	@PutMapping(value = "/bookings")
	public ResponseEntity<Booking> updateBooking(@RequestParam int bookingId, @RequestBody Booking booking) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBooking(bookingId, booking));
	}

}