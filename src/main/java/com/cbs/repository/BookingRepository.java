package com.cbs.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cbs.entity.Booking;
import com.cbs.entity.Car;
import com.cbs.entity.User;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {

	@Query("SELECT b FROM Booking b WHERE b.user =:user and b.bookingFromDate >=:bookingFromDate and b.bookingToDate <=:bookingToDate")
	public Page<Booking> findAllUserBooking(@Param("user") User user,
			@Param("bookingFromDate") LocalDateTime bookingFromDate,
			@Param("bookingToDate") LocalDateTime bookingToDate, Pageable pageable);

	@Query("SELECT b FROM Booking b WHERE b.car =:car and b.bookingFromDate >=:bookingFromDate and b.bookingToDate <=:bookingToDate")
	public Page<Booking> findAllCarBooking(@Param("car") Car car,
			@Param("bookingFromDate") LocalDateTime bookingFromDate,
			@Param("bookingToDate") LocalDateTime bookingToDate, Pageable pageable);

	public Booking findByUser(User user);
}
