package com.solvd.socialNetwork.controller;

import com.solvd.socialNetwork.dao.jdbcMySQLImpl.UserDaoImpl;
import com.solvd.socialNetwork.model.user.User;
import com.solvd.socialNetwork.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    final static Logger LOGGER = LogManager.getLogger(HomeController.class);


    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("loginController", "Status");
        model.addAttribute("userCount", userService.countUsers());
        return "index";
    }

    @PostMapping("/loginAttempt")
    public String handleLogin(@RequestParam("username") String user, @RequestParam("password") String password, Model model) {
        if (userService.validateUser(user, password)) {
            model.addAttribute("loginController", "Successful login!");
            model.addAttribute("userCount", userService.countUsers());
            return "index";
        }
        model.addAttribute("loginController", "Failed login!");
        return "index";
    }

    @PostMapping("/createAccount")
    public String handleCreateAccount(@RequestParam("create_username") String newUser,
                                      @RequestParam("create_password") String newPass, Model model) {
        if (userService.validateUniqueUser(newUser)) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User(newUser, newPass);
            try {
                userDao.create(user);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            model.addAttribute("loginController", "Account successfully created");
            model.addAttribute("userCount", userService.countUsers());
            return "index";
        }
        model.addAttribute("loginController", "Please choose a unique username");
        model.addAttribute("userCount", userService.countUsers());
        return "index";
    }
}