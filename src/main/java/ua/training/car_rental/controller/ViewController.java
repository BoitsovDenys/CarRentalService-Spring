package ua.training.car_rental.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String getindex() {
        return "index";
    }

//    @GetMapping("/login")
//    public String getLogin() {
//        return "login";
//    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin";
    }

    @GetMapping("/manager")
    public String getManager() {
        return "manager/manager";
    }

    @GetMapping("/user")
    public String getUser() {
        return "user/user";
    }
}
