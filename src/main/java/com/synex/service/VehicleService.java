package com.synex.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synex.domain.Vehicle;
import com.synex.repository.VehicleRepository;

import java.util.List;

@Service
@Transactional
public class VehicleService {

	private final VehicleRepository repo;

	public VehicleService(VehicleRepository repo) {
		this.repo = repo;
	}

	@Transactional(readOnly = true)
	public List<Vehicle> findAll() {
		return repo.findAll();
	}

	@Transactional(readOnly = true)
	public Vehicle findByReg(String reg) {
		return repo.findByRegistrationNumber(reg).orElse(null);
	}

	public Vehicle save(Vehicle v) {
		return repo.save(v);
	}

	public void deleteByReg(String reg) {
		repo.deleteByRegistrationNumber(reg);
	}
}
