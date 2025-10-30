package com.synex;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.synex.domain.Car;
import com.synex.domain.Vehicle;
import com.synex.repository.VehicleRepository;
import com.synex.service.VehicleService;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class VehicleServiceTest {

	VehicleRepository repo = Mockito.mock(VehicleRepository.class);
	VehicleService service = new VehicleService(repo);

	@Test
	void testFindAllVehicles() {
		Car car = new Car("Toyota", "Corolla", 2020, "ABC123", 4);
		Mockito.when(repo.findAll()).thenReturn(List.of(car));

		List<Vehicle> result = service.findAll();

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getMake()).isEqualTo("Toyota");
	}
}
