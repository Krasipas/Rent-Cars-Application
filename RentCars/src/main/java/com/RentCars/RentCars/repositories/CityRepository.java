package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
