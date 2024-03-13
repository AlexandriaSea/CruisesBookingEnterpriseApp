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

    @Autowired
    private BookingRepository bookRepository;

    @Autowired
    private PassengerRepository passRepository;

    @Autowired
    private CruiseRepository cruiseRepository;

    @GetMapping("/cruise/booking")
    public String bookingPage(Booking booking, Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("cruises", cruiseRepository.findAll());
        return "cruise/booking";
    }

    @PostMapping("/cruise/booking")
    public String bookCruise(@Valid Booking booking, BindingResult result, Model model, RedirectAttributes redirAttrs,
                             HttpSession session, @RequestParam("selectedCruiseId") int cruiseId) {
        Integer passengerId = (Integer) session.getAttribute("passengerId");
        System.out.println(passengerId);

        if (result.hasErrors()) {
            redirAttrs.addFlashAttribute("errorMessage", "Invalid booking details.");
            return "redirect:/cruise/booking";
        }

        try {
            Passenger registeredPassenger = passRepository.findById(passengerId).orElse(null);
            booking.setPassenger(registeredPassenger);

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

    @GetMapping("/cruise/checkout")
    public String checkoutPage(Booking booking, Model model, HttpSession session) {
        Integer reservationId = (Integer) session.getAttribute("reservationId");
        model.addAttribute("reservationId", reservationId);

        Double totalPrice = (Double) session.getAttribute("totalPrice");
        model.addAttribute("totalPrice", totalPrice);

        return "cruise/checkout";
    }

    @PostMapping("/cruise/confirmation")
    public String confirmationPage(Booking booking, Model model, HttpSession session) {
        Integer reservationId = (Integer) session.getAttribute("reservationId");
        model.addAttribute("reservationId", reservationId);

        return "cruise/confirmation";
    }

    @GetMapping("/cruise/modify/{reservationId}/{cruiseId}")
    public String modifyPage(@PathVariable("reservationId") int reservationId, @PathVariable("cruiseId") int cruiseId,
                             Booking booking, Model model, HttpSession session) {
        Booking selectedBooking = bookRepository.findById(reservationId).orElse(null);

        model.addAttribute("booking", selectedBooking);
        model.addAttribute("passengerId", session.getAttribute("passengerId"));
        model.addAttribute("cruiseId", cruiseId);

        return "cruise/modify";
    }

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
            Passenger passenger = passRepository.findById(passengerId).orElse(null);
            modifiedBooking.setPassenger(passenger);

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
