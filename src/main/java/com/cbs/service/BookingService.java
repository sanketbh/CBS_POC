package com.cbs.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cbs.entity.Booking;
import com.cbs.entity.Car;
import com.cbs.entity.User;
import com.cbs.exception.NotFoundException;
import com.cbs.repository.BookingRepository;
import com.cbs.repository.CarRepository;
import com.cbs.repository.UserRepository;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	// new car booking
	@Override
	public Booking addNewBooking(Booking booking) {
		booking = bookingRepository.save(booking);
		return booking;
	}

	// get All users booking for specified date(duration)
	@Override
	public Map<String, Object> getAllUsersBooking(String email, LocalDateTime bookingFromDate, LocalDateTime bookingToDate,
			int pageNumber, int itemsPerPage) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("User with email not Found"));

		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		
		Page<Booking> bookings = bookingRepository.findAllUserBooking(user, bookingFromDate, bookingToDate, pageable);
		
		Map<String, Object> response = new HashMap<>();
		response.put("bookings", bookings.getContent());
		response.put("currentPage", bookings.getNumber());
		response.put("totalItems", bookings.getTotalElements());
		response.put("totalPages", bookings.getTotalPages());

		return response;
	}

	// get all car which are booked
	@Override
	public Map<String, Object> getAllCarBooking(int id, LocalDateTime bookingFromDate, LocalDateTime bookingToDate,
			int pageNumber, int itemsPerPage) {
		Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not Found"));

		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		Page<Booking> bookings = bookingRepository.findAllCarBooking(car, bookingFromDate, bookingToDate, pageable);
		
		Map<String, Object> response = new HashMap<>();
		response.put("bookings", bookings.getContent());
		response.put("currentPage", bookings.getNumber());
		response.put("totalItems", bookings.getTotalElements());
		response.put("totalPages", bookings.getTotalPages());

		return response;
	}

	// get all car with valid insurance
	@Override
	public Map<String, Object> getAllCarWithValidInsurance(LocalDateTime insuranceTill, int pageNumber, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		
		Page<Car> cars = carRepository.findAllCarWithValidInsurance(insuranceTill, pageable);
		Map<String, Object> response = new HashMap<>();
		response.put("cars", cars.getContent());
		response.put("currentPage", cars.getNumber());
		response.put("totalItems", cars.getTotalElements());
		response.put("totalPages", cars.getTotalPages());
		return response;
	}
}