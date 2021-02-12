package com.cbs.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cbs.entity.Car;
import com.cbs.repository.CarRepository;

@Service
public class CarService implements ICarService {

	@Autowired
	private CarRepository carRepository;

	// new car booking
	@Override
	public Car addNewCar(Car car) {
		car = carRepository.save(car);
		return car;
	}

	// get all car with valid insurance
	@Override
	public Map<String, Object> getAllCarWithValidInsurance(LocalDateTime insuranceTill, int pageNumber,
			int itemsPerPage) {
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
