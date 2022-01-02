package com.RentCars.RentCars.controllers;

import com.RentCars.RentCars.entities.User;
import com.RentCars.RentCars.entities.UserCar;
import com.RentCars.RentCars.payload.request.RentRequest;
import com.RentCars.RentCars.payload.response.RentResponse;
import com.RentCars.RentCars.repositories.CarRepository;
import com.RentCars.RentCars.repositories.UserCarRepository;
import com.RentCars.RentCars.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rent")
public class UserCarController {
    private final UserCarRepository userCarRepo;
    private final UserRepository userRepo;
    private final CarRepository carRepo;

    public UserCarController(UserCarRepository userCarRepo, UserRepository userRepo, CarRepository carRepo) {
        this.userCarRepo = userCarRepo;
        this.userRepo = userRepo;
        this.carRepo = carRepo;
    }

    @GetMapping("/fetch")
    public List<RentResponse> getAllOrders(){
        List<UserCar> rents = userCarRepo.findAll();
        List<RentResponse> rentResponseList = new ArrayList<>();
        for(UserCar rent: rents){
            RentResponse rentResponse = new RentResponse();
            rentResponse.setUserName(rent.getUser().getFullName());
            rentResponse.setCar(rent.getCar().getBrandAndModel());
            rentResponse.setStartDate(rent.getStartDate());
            rentResponse.setFinishDate(rent.getFinishDate());

            rentResponseList.add(rentResponse);
        }
        return rentResponseList;
    }

    @PostMapping("/car")
    public ResponseEntity<?> rentCar(@RequestBody RentRequest rentRequest){
        User user = userRepo.findUserByNum(rentRequest.getUserNum());
        userCarRepo.save(new UserCar(
            user,
            rentRequest.getCar(),
            new Timestamp(System.currentTimeMillis()),
            rentRequest.getFinishDate()));
        return ResponseEntity.ok("New rent added!");

    }
}
