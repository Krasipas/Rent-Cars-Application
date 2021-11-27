package com.RentCars.RentCars.dataSeed;

import com.RentCars.RentCars.entities.Brand;

class BrandGenerator {
    static Brand[] brandsGenerator(){
        String[] brands = {"Audi", "VW", "BMW", "Mercedes"};
        Brand[] result = new Brand[brands.length];

        for(int i = 0; i < brands.length; i++){
            result[i] = new Brand(brands[i]);
        }

        return result;
    }
}
