package com.RentCars.RentCars.dataSeed;

import com.RentCars.RentCars.entities.Car;
import com.RentCars.RentCars.entities.User;
import com.RentCars.RentCars.entities.UserCar;

import java.util.Date;

public class UsersCarsGenerator {
     static UserCar[] CreateUsersCars(User[] users, Car[] cars){ // already initialised users and cars
        int recordsCount = Math.min(users.length, cars.length);

        UserCar[] result = new UserCar[recordsCount];

        for(int i = 0; i < recordsCount; i++){
            result[i] = new UserCar(users[i],cars[recordsCount - i - 1], null, null);
        }

        return result;
     }
}
