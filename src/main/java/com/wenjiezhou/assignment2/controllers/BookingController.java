package com.wenjiezhou.assignment2.controllers;

import com.wenjiezhou.assignment2.models.Booking;
import com.wenjiezhou.assignment2.models.Cruise;
import com.wenjiezhou.assignment2.models.Passenger;
import com.wenjiezhou.assignment2.repositories.BookingRepository;
import com.wenjiezhou.assignment2.repositories.CruiseRepository;
import com.wenjiezhou.assignment2.repositories.PassengerRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
Student Name: Wenjie Zhou
Student Number: 301337168
Submission Date: Mar 13, 2024
*/

@Controller
public class BookingController {

    // Link three repositories
    @Autowired
    private BookingRepository bookRepository;

    @Autowired
    private PassengerRepository passRepository;

    @Autowired
    private CruiseRepository cruiseRepository;

    // Get to booking page
    @GetMapping("/cruise/booking")
    public String bookingPage(Booking booking, Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("cruises", cruiseRepository.findAll());
        return "cruise/booking";
    }

    // Post to booking page and save a valid booking into database
    @PostMapping("/cruise/booking")
    public String bookCruise(@Valid Booking booking, BindingResult result, Model model, RedirectAttributes redirAttrs,
                             HttpSession session, @RequestParam("selectedCruiseId") int cruiseId) {
        if (result.hasErrors()) {
            redirAttrs.addFlashAttribute("errorMessage", "Invalid booking details.");
            return "redirect:/cruise/booking";
        }

        try {
            // Find and set passenger based on ID from session
            Integer passengerId = (Integer) session.getAttribute("passengerId");
            Passenger registeredPassenger = passRepository.findById(passengerId).orElse(null);
            booking.setPassenger(registeredPassenger);

            // Find and set cruise based on ID from request param
            Cruise selectedCruise = cruiseRepository.findById(cruiseId).orElse(null);
            booking.setCruise(selectedCruise);

            bookRepository.save(booking);
            session.setAttribute("reservationId", booking.getReservationId());
            session.setAttribute("totalPrice", booking.getTotalPrice());

            redirAttrs.addFlashAttribute("successMessage", "Booking successful.");
            return "redirect:/cruise/checkout";
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cruise/booking";
        }
    }

    // Get to checkout page
    @GetMapping("/cruise/checkout")
    public String checkoutPage(Booking booking, Model model, HttpSession session) {
        Integer reservationId = (Integer) session.getAttribute("reservationId");
        model.addAttribute("reservationId", reservationId);

        Double totalPrice = (Double) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice);

        return "cruise/checkout";
    }

    // Post to confirmation page, ignore form data, and simply display successful message
    @PostMapping("/cruise/confirmation")
    public String confirmationPage(Booking booking, Model model, HttpSession session) {
        Integer reservationId = (Integer) session.getAttribute("reservationId");
        model.addAttribute("reservationId", reservationId);

        return "cruise/confirmation";
    }

    // Get to modify page and add necessary attributes into model based on path variables from URL and ID from session
    @GetMapping("/cruise/modify/{reservationId}/{cruiseId}")
    public String modifyPage(@PathVariable("reservationId") int reservationId, @PathVariable("cruiseId") int cruiseId,
                             Booking booking, Model model, HttpSession session) {
        Booking selectedBooking = bookRepository.findById(reservationId).orElse(null);

        model.addAttribute("booking", selectedBooking);
        model.addAttribute("passengerId", session.getAttribute("passengerId"));
        model.addAttribute("cruiseId", cruiseId);

        return "cruise/modify";
    }

    // Post to modify page and save modified booking into database
    @PostMapping("/cruise/modify")
    public String modifyBooking(@Valid Booking modifiedBooking, BindingResult result, Model model,
                                RedirectAttributes redirAttrs,
                                @RequestParam("passengerId") int passengerId,
                                @RequestParam("cruiseId") int cruiseId) {
        if (result.hasErrors()) {
            redirAttrs.addFlashAttribute("errorMessage", "Invalid modified details.");
            return "redirect:/cruise/modify/{reservationId}";
        }

        try {
            // Find and set passenger based on ID from request param
            Passenger passenger = passRepository.findById(passengerId).orElse(null);
            modifiedBooking.setPassenger(passenger);

            // Find and set cruise based on ID from request param
            Cruise cruise = cruiseRepository.findById(cruiseId).orElse(null);
            modifiedBooking.setCruise(cruise);

            bookRepository.save(modifiedBooking);
            redirAttrs.addFlashAttribute("successMessage", "Modification successful.");
            return "redirect:/user/history";
        } catch (Exception e) {
            redirAttrs.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/user/history";
        }
    }
}
