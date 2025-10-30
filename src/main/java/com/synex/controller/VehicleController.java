package com.synex.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.synex.domain.Car;
import com.synex.domain.Truck;
import com.synex.domain.Vehicle;
import com.synex.service.VehicleService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	private final VehicleService service;

	public VehicleController(VehicleService service) {
		this.service = service;
	}

	@GetMapping
	public List<Map<String, Object>> listAll() {
		log.debug("GET /api/vehicles");
		return service.findAll().stream().map(this::toView).toList();
	}

	@GetMapping("/cars")
	public List<Map<String, Object>> listCars() {
		log.debug("GET /api/vehicles/cars");
		return service.findAll().stream().filter(v -> v instanceof Car).map(this::toView).toList();
	}

	@GetMapping("/trucks")
	public List<Map<String, Object>> listTrucks() {
		log.debug("GET /api/vehicles/trucks");
		return service.findAll().stream().filter(v -> v instanceof Truck).map(this::toView).toList();
	}

	@GetMapping("/registration/{reg}")
	public ResponseEntity<Map<String, Object>> getByRegistration(@PathVariable String reg) {
		Vehicle v = service.findByReg(reg);
		if (v == null) {
			log.debug("GET /api/vehicles/registration/" + reg + " does not exists");
			return ResponseEntity.notFound().build();
		}
		log.debug("GET /api/vehicles/registration/" + reg + " found");
		return ResponseEntity.ok(toView(v));
	}

	private Map<String, Object> toView(Vehicle v) {
		Map<String, Object> m = new LinkedHashMap<>();
		String type = (v instanceof Car) ? "CAR" : (v instanceof Truck) ? "TRUCK" : "VEHICLE";
		m.put("type", type);
		m.put("make", v.getMake());
		m.put("model", v.getModel());
		m.put("year", v.getYear());
		m.put("registrationNumber", v.getRegistrationNumber());
		if (v instanceof Car c)
			m.put("numberOfDoors", c.getNumberOfDoors());
		if (v instanceof Truck t)
			m.put("cargoCapacity", t.getCargoCapacity());
		m.put("details", type + ": " + v.getMake() + " " + v.getModel() + " (" + v.getYear() + "), Reg: "
				+ v.getRegistrationNumber());
		return m;
	}
}

//@RestController
//@RequestMapping("/api/vehicles")
//public class VehicleController {
//
//	private final List<Vehicle> vehicles = List.of(new Car("Toyota", "Corolla", 2020, "ABC-123", 4),
//			new Car("Honda", "Civic", 2019, "REG-101", 4), new Truck("Volvo", "FH16", 2022, "TRK-987", 18.0),
//			new Truck("Ford", "F-750", 2021, "REG-202", 12.5));
//
//	private final Map<String, Vehicle> byRegistration = vehicles.stream()
//			.collect(Collectors.toMap(Vehicle::getRegistrationNumber, v -> v, (a, b) -> a, LinkedHashMap::new));
//
//	@GetMapping
//	public List<Map<String, Object>> listAll() {
//		return vehicles.stream().map(this::toView).toList();
//	}
//
//	@GetMapping("/cars")
//	public List<Map<String, Object>> listCars() {
//		return vehicles.stream().filter(v -> v instanceof Car).map(this::toView).toList();
//	}
//
//	@GetMapping("/trucks")
//	public List<Map<String, Object>> listTrucks() {
//		return vehicles.stream().filter(v -> v instanceof Truck).map(this::toView).toList();
//	}
//
//	@GetMapping("/registration/{reg}")
//	public ResponseEntity<Map<String, Object>> getByRegistration(@PathVariable String reg) {
//		Vehicle v = byRegistration.get(reg);
//		if (v == null)
//			return ResponseEntity.notFound().build();
//		return ResponseEntity.ok(toView(v));
//	}
//
//	private Map<String, Object> toView(Vehicle v) {
//		Map<String, Object> m = new LinkedHashMap<>();
//		String type = (v instanceof Car) ? "CAR" : (v instanceof Truck) ? "TRUCK" : "VEHICLE";
//		m.put("type", type);
//		m.put("make", v.getMake());
//		m.put("model", v.getModel());
//		m.put("year", v.getYear());
//		m.put("registrationNumber", v.getRegistrationNumber());
//		if (v instanceof Car c)
//			m.put("numberOfDoors", c.getNumberOfDoors());
//		if (v instanceof Truck t)
//			m.put("cargoCapacity", t.getCargoCapacity());
//		m.put("details", type + ": " + v.getMake() + " " + v.getModel() + " (" + v.getYear() + "), Reg: "
//				+ v.getRegistrationNumber());
//		return m;
//	}
//}
