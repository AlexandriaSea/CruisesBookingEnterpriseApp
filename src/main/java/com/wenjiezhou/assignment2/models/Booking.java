package com.wenjiezhou.assignment2.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "reservationid")
    @NotNull(message = "Reservation ID is mandatory")
    @Positive(message = "Reservation ID must be positive")
    private int reservationId;

    @Column(name = "passengerid")
    @NotBlank(message = "Passenger ID is mandatory")
    private int passengerId;

    @Column(name = "cruiseid")
    @NotBlank(message = "Cruise ID is mandatory")
    private int cruiseId;

    @Column(name = "stateroomtype")
    @NotBlank(message = "Stateroom Type is mandatory")
    private String stateroomType;

    @Column(name = "totalguest")
    @NotBlank(message = "Total Guest is mandatory")
    private int totalGuest;

    @Column(name = "totalprice")
    @NotBlank(message = "Total Price is mandatory")
    private double totalPrice;

    public Booking(){}

    public Booking(int reservationId, int passengerId, int cruiseId, String stateroomType,
                   int totalGuest, double totalPrice)
    {
        super();
        this.reservationId = reservationId;
        this.passengerId = passengerId;
        this.cruiseId = cruiseId;
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

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
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
}
