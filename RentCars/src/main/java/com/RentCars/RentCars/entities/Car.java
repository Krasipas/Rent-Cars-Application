package com.RentCars.RentCars.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "brand")
    @NotNull
    private Brand brand;

    @Column(name = "model")
    @NotNull
    private String model;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    @Column(name = "date_manufactured")
    private Date date;

    @Column(name = "registration_num", length = 10)
    @NotNull
    private String registrationNum;

    @OneToMany(mappedBy = "car")
    private Set<UserCar> carUsers;

    public Car(Brand brand, String model, Category category, Fuel fuel, Set<UserCar> carUsers, String registrationNum, Date datemanufactured) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.fuel = fuel;
        this.carUsers = carUsers;
        this.registrationNum = registrationNum;
        this.date = datemanufactured;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public String getBrandAndModel(){
        return brand.getName() + ", " + model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Set<UserCar> getCarUsers() {
        return carUsers;
    }

    public void setCarUsers(Set<UserCar> carUsers) {
        this.carUsers = carUsers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }
}