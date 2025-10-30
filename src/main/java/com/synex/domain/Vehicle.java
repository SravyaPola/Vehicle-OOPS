package com.synex.domain;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
//Tells JPA/Hibernate you’re using one physical table for the entire class hierarchy 
//(base + all subclasses).
//All fields from every subclass live in the same table; 
//columns not used by a given row are NULL.
//JOINED: normalized tables per subclass (joins on read). Fewer nulls, more joins.
//TABLE_PER_CLASS: one table per subclass (no joins), but unions for polymorphic queries.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Adds a special column (here named vehicle_type) to store which subclass each row represents.
//JPA uses this value to instantiate the correct Java type when reading.
//Pair it with @DiscriminatorValue("CAR"), @DiscriminatorValue("TRUCK") on subclasses.
//If you omit @DiscriminatorValue, Hibernate uses the entity class name.
@DiscriminatorColumn(name = "vehicle_type")
public abstract class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	protected String make;

	@Column(nullable = false)
	protected String model;

	@Column(name = "vehicle_year", nullable = false)
	protected int year;

	@Column(nullable = false, unique = true)
	protected String registrationNumber;

	protected Vehicle() {
	}

	public Vehicle(String make, String model, int year, String registrationNumber) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.registrationNumber = registrationNumber;
	}

	public Long getId() {
		return id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public abstract void displayDetails();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Vehicle v))
			return false;
		return Objects.equals(registrationNumber, v.registrationNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(registrationNumber);
	}
}
