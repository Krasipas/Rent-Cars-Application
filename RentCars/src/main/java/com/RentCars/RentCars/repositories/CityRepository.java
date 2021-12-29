package com.RentCars.RentCars.repositories;

import com.RentCars.RentCars.entities.City;
import com.RentCars.RentCars.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c " +
            "FROM City c " +
            "WHERE " +
            "lower(c.name) " +
            "LIKE :#{#name == null || #name.isEmpty()? '%' : '%'+ #name +'%'} ")
    Page<City> filterCity(Pageable pageable, String name);

    City findCityByName(String name);

    @Query("SELECT u " +
            "FROM User u " +
            "INNER JOIN City c ON c.id = u.city.id " +
            "WHERE " +
            "lower(c.name) " +
            "LIKE :#{'%' + cityName + '%'} " )
    Page<User> findUsersByCityPageable(String cityName, Pageable pageable);
}
