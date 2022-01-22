package com.solvd.socialNetwork.service;

import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService  {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private static final String GET_USER_BY_LOGIN = "Select * from user where username=? and password=?";

    public void close(AutoCloseable resource) throws Exception {
        assert resource != null;
        try {
            resource.close();
        } catch (Exception e) {
            LOGGER.info(e);
        }
    }


    public static boolean validateUser(String username, String pass) {
        boolean st = false;
        Connection connection = null;
        PreparedStatement statement = null;
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
        } finally {
            assert statement != null;
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return st;
    }
}
