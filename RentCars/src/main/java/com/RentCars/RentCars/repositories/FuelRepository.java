package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Fuel findFuelByName(String name);
}
