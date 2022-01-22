package com.solvd.socialNetwork.controller;


import com.solvd.socialNetwork.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @PostMapping("login")
    public String handle(@RequestParam("username") String user, @RequestParam("password") String password) {
        if (UserService.validateUser(user, password)) {
            return "success";
        }
        return "failed";
    }


}
