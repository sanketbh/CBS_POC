package com.cbs.testcase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cbs.entity.Booking;
import com.cbs.entity.Car;
import com.cbs.entity.User;
import com.cbs.repository.BookingRepository;
import com.cbs.repository.CarRepository;
import com.cbs.repository.UserRepository;
import com.cbs.service.BookingService;

public class BookingTest {

	@InjectMocks
	private BookingService bookingService;

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	CarRepository carRepository;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAllUsersBooking() {
		int pageNumber = 0;
		int itemsPerPage = 5;

		User u = new User();
		u.setName("sanket");
		u.setEmail("sanket@gmail.com");

		Optional<User> user = Optional.of(u);
		Car c = new Car();
		c.setModel("Hyundai Elentra");
		c.setInsuranceTill(LocalDateTime.now());

		Optional<Car> car = Optional.of(c);

		Booking booking = new Booking();
		booking.setBookingFromDate(LocalDateTime.now());
		booking.setBookingToDate(LocalDateTime.now());

		List<Booking> bookings = new ArrayList<>();
		booking.setUser(u);
		booking.setCar(c);
		bookings.add(booking);
		when(userRepository.findByEmail(user.get().getEmail())).thenReturn(user);

		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		
		Page<Booking> bookingsPage = new PageImpl<Booking>(bookings, pageable, bookings.size());

		Map<String, Object> response = new HashMap<>();
		response.put("bookings", bookingsPage.getContent());
		response.put("currentPage", bookingsPage.getNumber());
		response.put("totalItems", bookingsPage.getTotalElements());
		response.put("totalPages", bookingsPage.getTotalPages());
		
		when(bookingRepository.findAllUserBooking(u, booking.getBookingFromDate(), booking.getBookingToDate(),
				pageable)).thenReturn(bookingsPage);

		Map<String, Object> bookingPage1 =  bookingService.getAllUsersBooking(u.getEmail(), booking.getBookingFromDate(),
				booking.getBookingToDate(), pageNumber, itemsPerPage);

     	assertEquals(bookingsPage.getContent(), bookingPage1.get("bookings"));
	}

	@Test
	public void getAllCarBooking() {
		int pageNumber = 0;
		int itemsPerPage = 5;

		User u = new User();
		u.setName("sanket");
		u.setEmail("sanket@gmail.com");

		Optional<User> user = Optional.of(u);

		Car c = new Car();
		c.setModel("Hyundai Elentra");
		c.setInsuranceTill(LocalDateTime.now());

		Optional<Car> car = Optional.of(c);
		Booking booking = new Booking();
		booking.setBookingFromDate(LocalDateTime.now());
		booking.setBookingToDate(LocalDateTime.now());

		List<Booking> bookings = new ArrayList<>();
		booking.setUser(u);
		booking.setCar(c);
		bookings.add(booking);

		when(carRepository.findById(car.get().getCarId())).thenReturn(car);

		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);

		Page<Booking> bookingsPage = new PageImpl<Booking>(bookings, pageable, bookings.size());

		Map<String, Object> response = new HashMap<>();
		response.put("bookings", bookingsPage.getContent());
		response.put("currentPage", bookingsPage.getNumber());
		response.put("totalItems", bookingsPage.getTotalElements());
		response.put("totalPages", bookingsPage.getTotalPages());
		
		when(bookingRepository.findAllCarBooking(c, booking.getBookingFromDate(), booking.getBookingToDate(), pageable))
				.thenReturn(bookingsPage);

		Map<String, Object> bookings1 = bookingService.getAllCarBooking(c.getCarId(), booking.getBookingFromDate(),
				booking.getBookingToDate(), pageNumber, itemsPerPage);
		assertEquals(bookingsPage.getContent(), bookings1.get("bookings"));
	}

	@Test
	public void getAllCarWithValidInsurance() {
		int pageNumber = 0;
		int itemsPerPage = 5;

		Car c = new Car();
		c.setModel("Hyundai Elentra");
		c.setInsuranceTill(LocalDateTime.now());

		List<Car> cars = new ArrayList<>();
		cars.add(c);

		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);

		Page<Car> carsPage = new PageImpl<Car>(cars, pageable, cars.size());

		Map<String, Object> response = new HashMap<>();
		response.put("cars", carsPage.getContent());
		response.put("currentPage", carsPage.getNumber());
		response.put("totalItems", carsPage.getTotalElements());
		response.put("totalPages", carsPage.getTotalPages());
		when(carRepository.findAllCarWithValidInsurance(c.getInsuranceTill(), pageable)).thenReturn(carsPage);

		Map<String, Object> cars_check = bookingService.getAllCarWithValidInsurance(c.getInsuranceTill(), pageNumber, itemsPerPage);
		assertEquals(carsPage.getContent(), cars_check.get("cars"));
	}

}
