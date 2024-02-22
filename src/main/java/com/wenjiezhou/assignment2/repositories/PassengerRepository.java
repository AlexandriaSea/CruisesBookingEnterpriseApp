package com.wenjiezhou.assignment2.repositories;


import com.wenjiezhou.assignment2.models.Passenger;
import org.springframework.data.repository.CrudRepository;


public interface PassengerRepository extends CrudRepository<Passenger, Integer>{

    Passenger findByUserNameAndPassword(String userName, String password);
}
