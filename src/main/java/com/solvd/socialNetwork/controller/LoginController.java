package com.solvd.socialNetwork.controller;


import com.solvd.socialNetwork.SocialNetworkApplication;
import com.solvd.socialNetwork.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    final static Logger LOGGER = LogManager.getLogger(LoginController.class);

    @PostMapping("login")
    public String handle(@RequestParam("username") String user, @RequestParam("password") String password) {
        if (UserService.validateUser(user, password)) {
            LOGGER.info("Successful login attempt " + user + password);
            return "success";
        }
        LOGGER.info("Failed login attempt " + user + password);
        return "failed";
    }


}
