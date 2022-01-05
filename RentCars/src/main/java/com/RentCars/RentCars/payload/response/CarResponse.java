package com.RentCars.RentCars.payload.response;

import com.RentCars.RentCars.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarResponse {
    private String Brand;
    private String Model;
    private List<UserResponse> Users = new ArrayList<>();

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public List<UserResponse> getUsers() {
        return Users;
    }

    public void setUsers(List<UserResponse> users) {
        Users = users;
    }
}
