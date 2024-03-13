package com.wenjiezhou.assignment2.controllers;

import com.wenjiezhou.assignment2.models.Booking;
import com.wenjiezhou.assignment2.models.Passenger;
import com.wenjiezhou.assignment2.repositories.BookingRepository;
import com.wenjiezhou.assignment2.repositories.PassengerRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/*
Student Name: Wenjie Zhou
Student Number: 301337168
Submission Date: Mar 13, 2024
*/

@Controller
public class PassengerController {

    // Link to two repositories
    @Autowired
    private PassengerRepository passRepository;

    @Autowired
    private BookingRepository bookRepository;

    // Get to registration page
    @GetMapping("/user/registration")
    public String registrationPage(Passenger passenger) {
        return "user/registration";
    }

    // Post to registration page and save a valid passenger into database if the userName has not been used
    @PostMapping("/user/registration")
    public String registerNewPassenger(@Valid Passenger passenger, BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "some fields validation failed.");
            return "user/registration";
        }

        Passenger registeredPassenger = passRepository.findByUserName(passenger.getUserName());
        if (registeredPassenger != null) {
            model.addAttribute("errorMessage", "Email has been used.");
            return "user/registration";
        }

        passRepository.save(passenger);
        redirAttrs.addFlashAttribute("successMessage", "Account has been created successfully.");
        return "redirect:/";
    }

    // Get to profile page, find passenger based on ID from session, and add passenger into model
    @GetMapping("/user/profile")
    public String profilePage(Passenger passenger, Model model, HttpSession session) {
        Integer passengerId = (Integer) session.getAttribute("passengerId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userName", userName);
        Passenger currentPassenger = passRepository.findById(passengerId).orElse(null);
        model.addAttribute("passenger", currentPassenger);

        return "user/profile";
    }

    // Post to profile page and saved updated passenger into database
    @PostMapping("/user/profile")
    public String updateProfile(@Valid Passenger updatedPassenger, BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "some fields validation failed.");
            return "user/profile";
        }

        passRepository.save(updatedPassenger);
        redirAttrs.addFlashAttribute("successMessage", "Profile has been updated successfully.");
        return "redirect:/user/profile";
    }

    // Get to history page, find all bookings based on passenger ID from session,
    // and add bookings and current date into model
    @GetMapping("/user/history")
    public String historyPage(Booking booking, Model model, HttpSession session) {
        Integer passengerId = (Integer) session.getAttribute("passengerId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userName", userName);
        model.addAttribute("bookings", bookRepository.findAllByPassengerPassengerId(passengerId));
        model.addAttribute("currentDate", new Date());

        return "user/history";
    }

    // Post to cancel a booking based on reservation ID from path variable from URL
    @PostMapping("/cancel/{reservationId}")
    public String cancelBooking(@PathVariable("reservationId") int reservationId, RedirectAttributes redirAttrs) {
        bookRepository.deleteById(reservationId);
        redirAttrs.addFlashAttribute("successMessage", "Booking has been cancelled successfully.");
        return "redirect:/user/history";
    }

    // Get to log out user session and return to homepage
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
