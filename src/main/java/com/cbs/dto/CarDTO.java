package com.cbs.dto;


import java.time.LocalDateTime;

public class CarDTO {

	private String model;
	private LocalDateTime insuranceTill;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public LocalDateTime getInsuranceTill() {
		return insuranceTill;
	}

	public void setInsuranceTill(LocalDateTime insuranceTill) {
		this.insuranceTill = insuranceTill;
	}

	@Override
	public String toString() {
		return "CarDTO [model=" + model + ", insuranceTill=" + insuranceTill + "]";
	}

}
