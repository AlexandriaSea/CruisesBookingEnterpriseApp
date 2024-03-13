package com.wenjiezhou.assignment2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

/*
Student Name: Wenjie Zhou
Student Number: 301337168
Submission Date: Mar 13, 2024
*/

@Entity
@Table(name = "cruise")
public class Cruise {

    @Id
    @Column(name = "cruiseid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cruiseId;

    @Column(name = "cruisename")
    @NotBlank(message = "Cruise Name is mandatory")
    private String cruiseName;

    @Column(name = "shipname")
    @NotBlank(message = "Ship Name is mandatory")
    private String shipName;

    @Column(name = "startdate")
    @NotNull(message = "Start Date is mandatory")
    private Date startDate;

    @Column(name = "enddate")
    @NotNull(message = "End Date is mandatory")
    private Date endDate;

    @Column(name = "destination")
    @NotBlank(message = "Destination is mandatory")
    private String destination;

    public Cruise() {
    }

    public Cruise(String cruiseName, String shipName, Date startDate, Date endDate, String destination) {
        super();
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
    }

    public Cruise(int cruiseId, String cruiseName, String shipName, Date startDate, Date endDate, String destination) {
        super();
        this.cruiseId = cruiseId;
        this.cruiseName = cruiseName;
        this.shipName = shipName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
