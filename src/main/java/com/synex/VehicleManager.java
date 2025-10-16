package com.synex;

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
	private List<Vehicle> vehicles;

	public VehicleManager() {
		this.vehicles = new ArrayList<>();
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void removeVehicle(String registrationNumber) {
		vehicles.removeIf(v -> v.getRegistrationNumber().equals(registrationNumber));
	}

	public void displayAllVehicles() {
		for (Vehicle v : vehicles) {
			v.displayDetails();
			System.out.println("---");
		}
	}
}
