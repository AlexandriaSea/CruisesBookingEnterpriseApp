package com.wenjiezhou.assignment2.controllers;


import com.wenjiezhou.assignment2.models.Passenger;
import com.wenjiezhou.assignment2.repositories.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomepageController {

    @Autowired
    private PassengerRepository passRepository;

    @RequestMapping("/index")
    public String homePage()
    {
        return "index";
    }

    @PostMapping("/login")
    public String login(@Valid Passenger passenger, BindingResult result, Model model, RedirectAttributes redirAttrs)
    {
        Passenger registeredPassenger = passRepository.findByUserNameAndPassword(
                passenger.getUserName(), passenger.getPassword());
        if(registeredPassenger != null)
        {
            redirAttrs.addFlashAttribute("successMessage", "Login successful.");
            return "redirect:/cruise/booking";
        }
        else
        {
            redirAttrs.addFlashAttribute("errorMessage", "Invalid username or password.");
            return "redirect:/index";
        }
    }

    @GetMapping("/user/registration")
    public String registrationPage()
    {
        return "user/registration";
    }
}
