package com.wenjiezhou.assignment2.controllers;


import com.wenjiezhou.assignment2.models.Passenger;
import com.wenjiezhou.assignment2.repositories.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PassengerController {

    @Autowired
    private PassengerRepository passRepository;

    @GetMapping("/user/registration")
    public String registrationPage(Passenger passenger)
    {
        return "user/registration";
    }

    @PostMapping("/user/registration")
    public String registerNewPassenger(@Valid Passenger passenger, BindingResult result, Model model, RedirectAttributes redirAttrs)
    {
        Passenger registeredPassenger = passRepository.findByUserName(
                passenger.getUserName());

        if (result.hasErrors())
        {
            model.addAttribute("errorMessage", "some fields validation failed.");
            return "user/registration";
        }

        if (registeredPassenger != null)
        {
            model.addAttribute("errorMessage", "Email has been used.");
            return "user/registration";
        }

        passRepository.save(passenger);
        redirAttrs.addFlashAttribute("successMessage", "Account has been created successfully.");
        return "redirect:/index";
    }
}
