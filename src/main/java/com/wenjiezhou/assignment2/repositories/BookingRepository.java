package com.wenjiezhou.assignment2.repositories;


import com.wenjiezhou.assignment2.models.Booking;
import org.springframework.data.repository.CrudRepository;


public interface BookingRepository extends CrudRepository<Booking, Integer>{
}
