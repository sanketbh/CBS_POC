package com.cbs.testcase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cbs.entity.Booking;
import com.cbs.entity.Car;
import com.cbs.entity.User;
import com.cbs.exception.NotFoundException;
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

		when(bookingRepository.findAllUserBooking(u, booking.getBookingFromDate(), booking.getBookingToDate(),
				pageable)).thenReturn(bookings);

		List<Booking> bookings1 = new ArrayList<>();
		bookings1 = bookingService.getAllUsersBooking(u.getEmail(), booking.getBookingFromDate(),
				booking.getBookingToDate(), pageNumber, itemsPerPage);

		assertEquals(bookings, bookings1);
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

		when(bookingRepository.findAllCarBooking(c, booking.getBookingFromDate(), booking.getBookingToDate(), pageable))
				.thenReturn(bookings);

		List<Booking> bookings1 = new ArrayList<>();
		bookings1 = bookingService.getAllCarBooking(c.getCarId(), booking.getBookingFromDate(),
				booking.getBookingToDate(), pageNumber, itemsPerPage);
		assertEquals(bookings, bookings1);
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

		when(carRepository.findAllCarWithValidInsurance(c.getInsuranceTill(), pageable)).thenReturn(cars);

		List<Car> cars_check = new ArrayList<>();
		cars_check = bookingService.getAllCarWithValidInsurance(c.getInsuranceTill(), pageNumber, itemsPerPage);
		assertEquals(cars, cars_check);
	}

}
