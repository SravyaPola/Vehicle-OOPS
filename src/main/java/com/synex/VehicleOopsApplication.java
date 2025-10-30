package com.synex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.synex.domain.Car;
import com.synex.domain.Truck;
import com.synex.repository.VehicleRepository;

@SpringBootApplication
public class VehicleOopsApplication {
	private static final Logger log = LoggerFactory.getLogger(VehicleOopsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VehicleOopsApplication.class, args);
	}

	@Bean
	CommandLineRunner seed(VehicleRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				log.info("Seeding initial data");
				repo.save(new Car("Toyota", "Corolla", 2020, "ABC-123", 4));
				repo.save(new Car("Honda", "Civic", 2019, "REG-101", 4));
				repo.save(new Truck("Volvo", "FH16", 2022, "TRK-987", 18.0));
				repo.save(new Truck("Ford", "F-750", 2021, "REG-202", 12.5));
				log.info("Seeded {} vehicles", repo.count());
			} else {
				log.info("Skipping seeding; {} records already present", repo.count());
			}
		};
	}

}
