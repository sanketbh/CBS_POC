package com.cbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cbs.entity.Booking;
import com.cbs.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	// new booking
	@PostMapping(value = "/bookings")
	public ResponseEntity<Booking> addNewBooking(@RequestBody Booking newBooking) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.addNewBooking(newBooking));
	}

	@PutMapping(value = "/bookings")
	public ResponseEntity<Booking> updateBooking(@RequestParam int bookingId, @RequestBody Booking booking) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateBooking(bookingId, booking));
	}
}
