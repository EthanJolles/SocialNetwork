package com.solvd.socialNetwork.controller;

import com.solvd.socialNetwork.dao.jdbcMySQLImpl.UserDaoImpl;
import com.solvd.socialNetwork.model.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class UserController {

    final static Logger LOGGER = LogManager.getLogger(UserController.class);

//    @RequestMapping("/user")
//    private User getAllUsers() {
//        UserDaoImpl userDao = new UserDaoImpl();
//        User user = null;
//        try {
//            user = userDao.getByUsername("test1");
//        } catch (SQLException e) {
//            LOGGER.error(e);
//        }
//        return user;
//    }
}
