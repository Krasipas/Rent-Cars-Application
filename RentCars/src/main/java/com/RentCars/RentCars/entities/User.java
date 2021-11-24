package com.RentCars.RentCars.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "isManager")
    private boolean isManager;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserCar> userCars;

    public User() {
    }

    public User(String firstName, String lastName, City city, boolean isManager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.isManager = isManager;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Set<UserCar> getUserCars() {
        return userCars;
    }

    public void setUserCars(Set<UserCar> userCars) {
        this.userCars = userCars;
    }
}
