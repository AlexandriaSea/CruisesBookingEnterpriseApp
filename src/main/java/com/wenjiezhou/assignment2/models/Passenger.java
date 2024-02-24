package com.wenjiezhou.assignment2.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @Column(name = "passengerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerId;

    @Column(name = "username")
    @NotBlank(message = "User Name is mandatory")
    @Email(message = "User Name is email")
    private String userName;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "firstname")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Column(name = "address")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @Column(name = "city")
    @NotBlank(message = "City is mandatory")
    private String city;

    @Column(name = "postalcode")
    @NotBlank(message = "Postal Code is mandatory")
    private String postalCode;

    @Column(name = "country")
    @NotBlank(message = "Country is mandatory")
    private String country;

    public Passenger(){}

    public Passenger(String userName, String password, String firstName, String lastName,
                     String address, String city, String postalCode, String country)
    {
        super();
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Passenger(int passengerId, String userName, String password, String firstName, String lastName,
                     String address, String city, String postalCode, String country)
    {
        super();
        this.passengerId = passengerId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
