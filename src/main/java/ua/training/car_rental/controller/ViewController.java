package ua.training.car_rental.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String get() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
