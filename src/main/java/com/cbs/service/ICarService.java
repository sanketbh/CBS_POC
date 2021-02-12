package com.cbs.service;

import java.time.LocalDateTime;
import java.util.Map;

import com.cbs.entity.Car;

public interface ICarService {
	public Car addNewCar(Car car);

	// get All cars which has valid insurance in specified time
	public Map<String, Object> getAllCarWithValidInsurance(LocalDateTime insuranceTill, int pageNumber,
			int itemsPerpage);
}
