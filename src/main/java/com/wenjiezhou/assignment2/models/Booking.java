package com.wenjiezhou.assignment2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
Student Name: Wenjie Zhou
Student Number: 301337168
Submission Date: Mar 13, 2024
*/

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "reservationid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    // Link to passenger table using foreign key of passengerId
    @ManyToOne
    @JoinColumn(name = "passengerid", referencedColumnName = "passengerid")
    private Passenger passenger;

    // Link to cruise table using foreign key of cruiseId
    @ManyToOne
    @JoinColumn(name = "cruiseid", referencedColumnName = "cruiseid")
    private Cruise cruise;

    @Column(name = "stateroomtype")
    @NotBlank(message = "Stateroom Type is mandatory")
    private String stateroomType;

    @Column(name = "totalguest")
    @Min(1)
    private int totalGuest;

    @Column(name = "totalprice")
    @NotNull(message = "Total Price is mandatory")
    private double totalPrice;

    public Booking() {
    }

    public Booking(Passenger passenger, Cruise cruise, String stateroomType,
                   int totalGuest, double totalPrice) {
        super();
        this.passenger = passenger;
        this.cruise = cruise;
        this.stateroomType = stateroomType;
        this.totalGuest = totalGuest;
        this.totalPrice = totalPrice;
    }

    public Booking(int reservationId, Passenger passenger, Cruise cruise, String stateroomType,
                   int totalGuest, double totalPrice) {
        super();
        this.reservationId = reservationId;
        this.passenger = passenger;
        this.cruise = cruise;
        this.stateroomType = stateroomType;
        this.totalGuest = totalGuest;
        this.totalPrice = totalPrice;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getStateroomType() {
        return stateroomType;
    }

    public void setStateroomType(String stateroomType) {
        this.stateroomType = stateroomType;
    }

    public int getTotalGuest() {
        return totalGuest;
    }

    public void setTotalGuest(int totalGuest) {
        this.totalGuest = totalGuest;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }
}
