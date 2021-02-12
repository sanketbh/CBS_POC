package com.cbs.dto;

import java.time.LocalDateTime;

public class BookingDTO {

	private UserDTO user;
	private CarDTO car;
	private LocalDateTime bookingFromDate;
	private LocalDateTime bookingToDate;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CarDTO getCar() {
		return car;
	}

	public void setCar(CarDTO car) {
		this.car = car;
	}

	public LocalDateTime getBookingFromDate() {
		return bookingFromDate;
	}

	public void setBookingFromDate(LocalDateTime bookingFromDate) {
		this.bookingFromDate = bookingFromDate;
	}

	public LocalDateTime getBookingToDate() {
		return bookingToDate;
	}

	public void setBookingToDate(LocalDateTime bookingToDate) {
		this.bookingToDate = bookingToDate;
	}

	@Override
	public String toString() {
		return "BookingDTO [user=" + user + ", car=" + car + ", bookingFromDate=" + bookingFromDate + ", bookingToDate="
				+ bookingToDate + "]";
	}

}
