package com.cbs.service;

import com.cbs.entity.Booking;

public interface IUserService {
	public Booking addNewBooking(Booking newBooking); // method to add new booking

	public Booking updateBooking(int bookingId, Booking booking);
	

}
