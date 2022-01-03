package com.RentCars.RentCars.controllers;

import com.RentCars.RentCars.entities.Car;
import com.RentCars.RentCars.entities.User;
import com.RentCars.RentCars.payload.request.CarRequest;
import com.RentCars.RentCars.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/car")
public class CarController {
    private final CarRepository carRepo;
    private final FuelRepository fuelRepo;
    private final CategoryRepository categoryRepo;
    private final BrandRepository brandRepo;

    public CarController(
            CarRepository carRepo,
            FuelRepository fuelRepo,
            CategoryRepository categoryRepo,
            BrandRepository brandRepo) {
        this.carRepo = carRepo;
        this.fuelRepo = fuelRepo;
        this.categoryRepo = categoryRepo;
        this.brandRepo = brandRepo;
    }

    @GetMapping("/fetch")
    public List<Car> getAllCars(){
        return carRepo.findAll();
    }

    @GetMapping("/find/model")
    public ResponseEntity<?> findCarByBrandAndModel(String Brand, String Model){
        List<Car> result = carRepo.findByBrandAndModel(Brand, Model);
        return ResponseEntity.ok(result != null ?
                result :
                "Not Found!");
    }

    @GetMapping("/find/brand")
    public ResponseEntity<?> findCarByBrand(String Brand){
        List<Car> result = carRepo.findByBrand(Brand);
        return ResponseEntity.ok(result != null ?
                result :
                "Not Found!");
    }

    @PostMapping("/save")
    public ResponseEntity<?> addCar(@RequestBody CarRequest carRequest){
        if(carRepo.findByRegistrationNum(carRequest.getRegistrationNum()) != null){
            return ResponseEntity.ok("Car already exists!");
        }

        if(brandRepo.findBrandByName(carRequest.getBrand()) == null){
            return ResponseEntity.ok("Our application doesn't with " + carRequest.getBrand() + " cars!");
        }

        if(fuelRepo.findFuelByName(carRequest.getFuel()) == null){
            return ResponseEntity.ok(carRequest.getFuel() + " is unknown fuel!");
        }

        if(categoryRepo.findCategoryByName(carRequest.getCategory()) == null){
            return ResponseEntity.ok(carRequest.getCategory() + " is not supported category!");
        }
    }
}
