package com.cbs.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableArgumentResolver;

import com.cbs.entity.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, Integer> {

	// get all cars with valid insurance
	@Query("SELECT c FROM Car c WHERE c.insuranceTill >=:insuranceTill")
	public List<Car> findAllCarWithValidInsurance(@Param("insuranceTill") LocalDateTime insuranceTill,
			Pageable pageable);

	public Optional<Car> findByCarId(int carId);
}