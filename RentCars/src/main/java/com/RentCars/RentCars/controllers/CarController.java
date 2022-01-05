package com.RentCars.RentCars.controllers;

import com.RentCars.RentCars.entities.*;
import com.RentCars.RentCars.payload.request.CarRequest;
import com.RentCars.RentCars.payload.response.CarResponse;
import com.RentCars.RentCars.payload.response.UserResponse;
import com.RentCars.RentCars.repositories.*;
import com.RentCars.RentCars.service.UserCarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/car")
public class CarController {
    private final CarRepository carRepo;
    private final FuelRepository fuelRepo;
    private final CategoryRepository categoryRepo;
    private final BrandRepository brandRepo;
    private final UserCarRepository userCarRepo;

    public CarController(
            CarRepository carRepo,
            FuelRepository fuelRepo,
            CategoryRepository categoryRepo,
            BrandRepository brandRepo,
            UserCarRepository userCarRepo) {
        this.carRepo = carRepo;
        this.fuelRepo = fuelRepo;
        this.categoryRepo = categoryRepo;
        this.brandRepo = brandRepo;
        this.userCarRepo = userCarRepo;
    }

    @GetMapping("/fetch")
    public List<CarResponse> getAllCars(){
        List<CarResponse> cars = new ArrayList<>();

        for(Car car: carRepo.findAll()){
            CarResponse currentCar = new CarResponse();
            currentCar.setBrand(car.getBrand().getName());
            currentCar.setModel(car.getModel());

            for(UserCar userCar : car.getCarUsers()){
                User currentUser = userCar.getUser();

                currentCar.getUsers().add(new UserResponse(
                        currentUser.getFirstName(),
                        currentUser.getLastName(),
                        currentUser.getNum(),
                        currentUser.getCity().getName(),
                        currentUser.getBirthDate(),
                        currentUser.isManager()));
            }

            cars.add(currentCar);
        }

        return cars;
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
    public ResponseEntity<?> addCar(@RequestBody CarRequest carRequest) {
        Brand selectedBrand = brandRepo.findBrandByName(carRequest.getBrand());
        Fuel selectedFuel = fuelRepo.findFuelByName(carRequest.getFuel());
        Category selectedCategory = categoryRepo.findCategoryByName(carRequest.getCategory());

        if(carRepo.findByRegistrationNum(carRequest.getRegistrationNum()) != null){
            return ResponseEntity.ok("Car already exists!");
        }

        if(selectedBrand == null){
            return ResponseEntity.ok("Our application doesn't with " + carRequest.getBrand() + " cars!");
        }

        if(selectedFuel == null){
            return ResponseEntity.ok(carRequest.getFuel() + " is unknown fuel!");
        }

        if(selectedCategory == null){
            return ResponseEntity.ok(carRequest.getCategory() + " is not supported category!");
        }

        Date dateManufactured = null;

        try{
            dateManufactured = new SimpleDateFormat("dd/MM/yyyy").parse(carRequest.getDateManufactured());
        }
        catch (ParseException parseException){
            return ResponseEntity.ok("Please enter date in correct format! dd/MM/yyyy");
        }

        Car car = new Car(
                selectedBrand,
                carRequest.getModel(),
                selectedCategory,
                selectedFuel,
                null,
                carRequest.getRegistrationNum(),
                dateManufactured);

        carRepo.save(car);

        return ResponseEntity.ok(carRequest.getBrand() + " " +
                carRequest.getModel() + " " +
                carRequest.getRegistrationNum() +
                " is added successfully!");
    }

    @DeleteMapping("/deleteByNum")
    public ResponseEntity<?> deleteCar(String registrationNum){
        Car selectedCar = carRepo.findByRegistrationNum(registrationNum);

        if(selectedCar == null){
            return ResponseEntity.ok("Car with registration number " + registrationNum + " was not found!");
        }

        boolean isCarBeingUsed = UserCarService.CheckIfCarIsBeingUsed(userCarRepo, selectedCar);

        if(isCarBeingUsed){
            return ResponseEntity.ok(
                    "Car with registration number " +
                    selectedCar.getRegistrationNum() +
                    " is being user and cannot be deleted from database!");
        }

        carRepo.delete(selectedCar);
        return ResponseEntity.ok("Successfully deleted car with registration number " + selectedCar.getRegistrationNum());
    }
}
