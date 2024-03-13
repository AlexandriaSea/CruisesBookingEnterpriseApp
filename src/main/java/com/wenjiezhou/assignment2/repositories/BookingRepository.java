package com.wenjiezhou.assignment2.repositories;

import com.wenjiezhou.assignment2.models.Booking;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/*
Student Name: Wenjie Zhou
Student Number: 301337168
Submission Date: Mar 13, 2024
*/

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findAllByPassengerPassengerId(Integer passengerId);
}
