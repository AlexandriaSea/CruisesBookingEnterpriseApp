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


@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookRepository;

    @Autowired
    private PassengerRepository passRepository;

    @Autowired
    private CruiseRepository cruiseRepository;

    @GetMapping("/cruise/booking")
    public String bookingPage(Booking booking, Model model, HttpSession session)
    {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("cruises", cruiseRepository.findAll());
        return "cruise/booking";
    }

    @PostMapping("/cruise/booking")
    public String bookCruise(@Valid Booking booking, BindingResult result, Model model, RedirectAttributes redirAttrs,
                             HttpSession session, @RequestParam("selectedCruiseId") int cruiseId)
    {
        Integer passengerId = (Integer) session.getAttribute("passengerId");
        System.out.println(passengerId);

        if(result.hasErrors())
        {
            redirAttrs.addFlashAttribute("errorMessage", "Invalid booking details." );
            return "redirect:/cruise/booking";
        }

        try
        {
            Passenger registeredPassenger = passRepository.findById(passengerId).orElse(null);
            booking.setPassenger(registeredPassenger);

            Cruise selectedCruise = cruiseRepository.findById(cruiseId).orElse(null);
            booking.setCruise(selectedCruise);


            bookRepository.save(booking);
            redirAttrs.addFlashAttribute("successMessage", "Booking successful.");
            return "redirect:/cruise/confirmation";
        }
        catch (Exception e)
        {
            redirAttrs.addFlashAttribute("errorMessage",e.getMessage());
            return "redirect:/cruise/booking";
        }
    }

    @GetMapping("/cruise/confirmation")
    public String confirmationPage(Booking booking)
    {
        return "/cruise/confirmation";
    }
}
