package com.cbs.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cbs.entity.Car;
import com.cbs.service.ICarService;

@RestController
public class CarController {

	@Autowired
	private ICarService carService;

	@PostMapping(value = "/cars")
	public ResponseEntity<Car> addNewCar(@RequestBody Car car) {
		return ResponseEntity.status(HttpStatus.OK).body(carService.addNewCar(car));
	}
	
	// get all cars which has valid insurance in given specified time
		@GetMapping("/cars/validinsurances")
		public ResponseEntity<Map<String, Object>> getAllCarWithValidInsurance(
				@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime insuranceTill,
				@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int itemsPerPage) {

			return ResponseEntity.status(HttpStatus.OK)
					.body(carService.getAllCarWithValidInsurance(insuranceTill, pageNumber, itemsPerPage));
		}
}
