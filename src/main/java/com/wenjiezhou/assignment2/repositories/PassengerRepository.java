package com.wenjiezhou.assignment2.repositories;

import com.wenjiezhou.assignment2.models.Passenger;
import org.springframework.data.repository.CrudRepository;

/*
Student Name: Wenjie Zhou
Student Number: 301337168
Submission Date: Mar 13, 2024
*/

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {

    Passenger findByUserName(String userName);

    Passenger findByUserNameAndPassword(String userName, String password);
}
