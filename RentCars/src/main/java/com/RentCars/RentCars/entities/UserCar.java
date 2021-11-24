package com.RentCars.RentCars.entities;

import com.RentCars.RentCars.entities.keys.UserCarKey;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity()
@Table(name = "users_cars")
public class UserCar {
    @EmbeddedId
    private UserCarKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name = "car_id")
    private Car car;

    public UserCar() {
    }

    public UserCar(User user, Car car) {
        this.user = user;
        this.car = car;
    }

    public UserCarKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
