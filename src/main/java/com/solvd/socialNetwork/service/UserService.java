package com.solvd.socialNetwork.service;

import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private static final String GET_USER_BY_LOGIN = "Select * from user where username=? and password=?";


    public static boolean validateUser(String username,String pass) {
        boolean st = false;
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            st = resultSet.next();
        }
        catch(Exception e) {
            LOGGER.error(e);
        }
        return st;
    }
}
