package com.synex;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	Optional<Vehicle> findByRegistrationNumber(String registrationNumber);

	void deleteByRegistrationNumber(String registrationNumber);
}