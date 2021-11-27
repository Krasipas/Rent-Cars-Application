package com.RentCars.RentCars.dataSeed;

import com.RentCars.RentCars.entities.City;

class CityGenerator {
    static City[] citiesGenerator(){
        String[] cities = {"Stara Zagora", "Varna", "Burgas", "Plovdiv"};
        City[] result = new City[cities.length];

        for(int i = 0; i < cities.length; i++){
            result[i] = new City(cities[i]);
        }

        return result;
    }
}
