package com.synex.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TRUCK")
public class Truck extends Vehicle {

	@Column
	private double cargoCapacity;

	protected Truck() {
		super();
	}

	public Truck(String make, String model, int year, String registrationNumber, double cargoCapacity) {
		super(make, model, year, registrationNumber);
		this.cargoCapacity = cargoCapacity;
	}

	@Override
	public void displayDetails() {
		System.out.println("Truck: " + make + " " + model + " (" + year + ")");
		System.out.println("Registration: " + registrationNumber);
		System.out.println("Cargo Capacity: " + cargoCapacity + " tons");
	}

	public double getCargoCapacity() {
		return cargoCapacity;
	}
}
