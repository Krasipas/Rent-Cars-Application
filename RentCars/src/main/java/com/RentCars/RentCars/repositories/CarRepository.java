package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
