package com.RentCars.RentCars.controllers;

import com.RentCars.RentCars.entities.Car;
import com.RentCars.RentCars.entities.User;
import com.RentCars.RentCars.entities.UserCar;
import com.RentCars.RentCars.repositories.CarRepository;
import com.RentCars.RentCars.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
public class DemoController {
    private final UserRepository userRepo;
    private final CarRepository carRepo;

    public DemoController(UserRepository userRepo, CarRepository carRepo) {
        this.userRepo = userRepo;
        this.carRepo = carRepo;
    }

    @GetMapping("show/user/cars")
    public List<Car> showUserCar(String firstName, String lastName){
        User selectedUser = userRepo.findByFirstNameAndLastName(firstName,lastName);
        List<Car> result = new ArrayList<>();

        for(UserCar userCar:selectedUser.getUserCars()){
           result.add(userCar.getCar());
       }

        return result;
    }

}
