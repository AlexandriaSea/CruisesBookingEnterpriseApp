package com.wenjiezhou.assignment2.controllers;


import com.wenjiezhou.assignment2.models.Passenger;
import com.wenjiezhou.assignment2.repositories.PassengerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomepageController {

    @Autowired
    private PassengerRepository passRepository;

    @RequestMapping("/")
    public String homePage()
    {
        return "index";
    }

    @PostMapping("/index")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        Model model, RedirectAttributes redirAttrs, HttpSession session)
    {
        Passenger registeredPassenger = passRepository.findByUserNameAndPassword(userName, password);

        if(registeredPassenger != null)
        {
            session.setAttribute("passengerId", registeredPassenger.getPassengerId());
            session.setAttribute("userName", userName);
            redirAttrs.addFlashAttribute("successMessage", "Login successful.");
            return "redirect:cruise/booking";
        }
        else
        {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "index";
        }
    }
}
