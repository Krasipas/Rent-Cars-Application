package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
