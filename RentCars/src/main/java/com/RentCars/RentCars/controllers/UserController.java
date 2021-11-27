package com.RentCars.RentCars.controllers;

import com.RentCars.RentCars.entities.City;
import com.RentCars.RentCars.entities.User;
import com.RentCars.RentCars.payload.request.UserRequest;
import com.RentCars.RentCars.repositories.CityRepository;
import com.RentCars.RentCars.repositories.UserRepository;
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
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepo;
    private final CityRepository cityRepo;

    public UserController(UserRepository userRepo, CityRepository cityRepo) {
        this.userRepo = userRepo;
        this.cityRepo = cityRepo;
    }

    @GetMapping("/fetch")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/pages")
    public ResponseEntity<?> getUserPages(@RequestParam(defaultValue = "") String fName,
                                          @RequestParam(defaultValue = "") String lName,
                                          @RequestParam(defaultValue = "1") int currentPage,
                                          @RequestParam(defaultValue = "3") int perPage){
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        Page<User> userPage = userRepo.filterUserPages(pageable, fName.toLowerCase(), lName.toLowerCase());
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("totalPages", userPage.getTotalPages());
        response.put("totalElements", userPage.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findUserByFullName(String fName, String lName){
        List<User> result = userRepo.findByFirstNameAndLastName(fName, lName);
        return ResponseEntity.ok(result.isEmpty()? "Not Found!" : result);
    }

    @GetMapping("/cities")
    public List<City> getAllCities(){
        return cityRepo.findAll();
    }

    @DeleteMapping("/delete")
    public String deleteUser(String fName, String lName){
        List<User> result = userRepo.findByFirstNameAndLastName(fName, lName);
        if(result.isEmpty()){
            return "User not found!";
        }
        for(User user: result){
            userRepo.delete(user);
        }
        return fName + lName + "deleted!";
    }

    @PostMapping("/save")
    public ResponseEntity<?> persistUser(@RequestBody UserRequest userRequest){
        List<User> users = userRepo.findByFirstNameAndLastName(userRequest.getFirstName(), userRequest.getLastName());
        if(users.isEmpty()) {
            for (User user : users) {
                user.setCity(userRequest.getCity());
                user.setBirthDate(userRequest.getBirthDate());
                user.setManager(userRequest.isManager());
                return ResponseEntity.ok("User" + user + "is saved!");
            }
        }
        return ResponseEntity.ok("User" + userRepo.save(new User(userRequest.getFirstName(),
                                                                 userRequest.getLastName(),
                                                                 userRequest.getCity(),
                                                                 userRequest.isManager(),
                                                                 userRequest.getBirthDate())) + "is saved!");
    }
}
