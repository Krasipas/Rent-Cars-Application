package com.RentCars.RentCars.controllers;

import com.RentCars.RentCars.entities.Car;
import com.RentCars.RentCars.entities.User;
import com.RentCars.RentCars.entities.UserCar;
import com.RentCars.RentCars.repositories.CarRepository;
import com.RentCars.RentCars.repositories.UserCarRepository;
import com.RentCars.RentCars.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/client")
public class DemoController {
    private final UserRepository userRepo;
    private final CarRepository carRepo;
    private final UserCarRepository userCarRepo;

    public DemoController(UserRepository userRepo, CarRepository carRepo, UserCarRepository userCarRepo) {
        this.userRepo = userRepo;
        this.carRepo = carRepo;
        this.userCarRepo = userCarRepo;
    }

    /*
    @GetMapping("show/user/cars")
    public List<Car> showUserCar(String firstName, String lastName){
        User selectedUser = userRepo.findByFirstNameAndLastName(firstName,lastName);
        List<Car> result = new ArrayList<>();

        for(UserCar userCar:selectedUser.getUserCars()){
           result.add(userCar.getCar());
       }

        return result;
    }

    @PostMapping("add/user/car")
    public UserCar addCarToUser(String firstName, String lastName, String carBrand, String carModel){
        User selectedUser = userRepo.findByFirstNameAndLastName(firstName, lastName);
        Car selectedCar = carRepo.findByBrandAndModel(carBrand,carModel);
        UserCar userCarToSave = new UserCar(selectedUser,selectedCar);

        return userCarRepo.save(userCarToSave);
    }
     */
}
