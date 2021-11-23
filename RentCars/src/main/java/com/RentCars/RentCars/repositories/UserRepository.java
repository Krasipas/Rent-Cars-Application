package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
