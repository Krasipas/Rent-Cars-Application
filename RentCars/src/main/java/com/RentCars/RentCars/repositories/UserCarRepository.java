package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.UserCar;
import com.RentCars.RentCars.entities.keys.UserCarKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepository extends JpaRepository<UserCar, UserCarKey> {

}
