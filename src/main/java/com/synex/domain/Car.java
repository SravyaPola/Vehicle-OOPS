package com.synex.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CAR")
public class Car extends Vehicle {

	@Column
	private int numberOfDoors;

	protected Car() {
		super();
	}

	public Car(String make, String model, int year, String registrationNumber, int numberOfDoors) {
		super(make, model, year, registrationNumber);
		this.numberOfDoors = numberOfDoors;
	}

	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	@Override
	public void displayDetails() {
		System.out.println("Car: " + make + " " + model + " (" + year + ")");
		System.out.println("Registration: " + registrationNumber);
		System.out.println("Doors: " + numberOfDoors);
	}
}