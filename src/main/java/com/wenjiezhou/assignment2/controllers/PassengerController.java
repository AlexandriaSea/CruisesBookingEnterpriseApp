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
public class PassengerController {

    @Autowired
    private PassengerRepository passRepository;
}
