package com.solvd.socialNetwork.controller;


import com.solvd.socialNetwork.dao.jdbcMySQLImpl.UserDaoImpl;
import com.solvd.socialNetwork.model.user.User;
import com.solvd.socialNetwork.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class LoginController {

    final static Logger LOGGER = LogManager.getLogger(LoginController.class);

    @PostMapping("login")
    public String handleLogin(@RequestParam("username") String user, @RequestParam("password") String password) {
        if (UserService.validateUser(user, password)) {
            LOGGER.info("Successful login attempt " + user + password);
            return "success";
        }
        LOGGER.info("Failed login attempt " + user + password);
        return "invalidLogin";
    }

    @PostMapping("create_account")
    public String handleCreateAccount(@RequestParam("create_username") String newUser,
                                      @RequestParam("create_password") String newPass) {
        if (UserService.validateUniqueUser(newUser)) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User(newUser, newPass);
            try {
                userDao.create(user);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
            return "success";
        }
        return "accountExists";
    }
}
