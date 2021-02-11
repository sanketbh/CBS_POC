package com.cbs.testcase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cbs.entity.Booking;
import com.cbs.entity.Car;
import com.cbs.entity.User;
import com.cbs.repository.BookingRepository;
import com.cbs.repository.CarRepository;
import com.cbs.repository.UserRepository;
import com.cbs.service.UserService;



public class UserTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private BookingRepository bookingRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CarRepository carRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void createBookingTest() {
		User u = new User();
		u.setUserId(0);
		u.setName("sanket");
		u.setEmail("sanket@gmail.com");
		
		Optional<User> user = Optional.of(u);
		Car c = new Car();
		c.setCarId(0);
		c.setModel("Hyundai Elentra");
		c.setInsuranceTill(LocalDateTime.now());
		
		Optional<Car> car = Optional.of(c);
		Booking newBooking = new Booking();
		newBooking.setUser(u);
		newBooking.setCar(c);
		newBooking.setBookingFromDate(LocalDateTime.now());
		newBooking.setBookingToDate(LocalDateTime.now());
		
		when(userRepository.findByUserId(1)).thenReturn(user);
		
		when(carRepository.findByCarId(c.getCarId())).thenReturn(car);
		
		when(bookingRepository.save(newBooking)).thenReturn(newBooking);
		
		Booking booking1 = userService.addNewBooking(newBooking); 
		
		//assertEquals(car.get().getModel(), booking.getCar().getModel());
		assertEquals(newBooking, booking1);
	}
}
