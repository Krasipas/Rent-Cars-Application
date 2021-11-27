package com.RentCars.RentCars.dataSeed;

import com.RentCars.RentCars.entities.Category;

class CategoryGenerator {
    static Category[] categoriesGenerator(){
        String[] categories = {"Sedan", "Wagon", "Coupe", "Hatchback"};
        Category[] result = new Category[categories.length];

        for(int i = 0; i < categories.length; i++){
            result[i] = new Category(categories[i]);
        }

        return result;
    }
}
