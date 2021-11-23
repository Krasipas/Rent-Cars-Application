package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
