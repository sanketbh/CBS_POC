package com.cbs.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewBookingDTO {
	private int userId;
	private int carId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalDateTime bookingFromDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalDateTime bookingToDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
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
		return "NewBookingDTO [userId=" + userId + ", carId=" + carId + ", bookingFromDate=" + bookingFromDate
				+ ", bookingToDate=" + bookingToDate + "]";
	}

}
