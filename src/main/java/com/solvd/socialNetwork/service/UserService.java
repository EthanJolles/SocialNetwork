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
    private static final String GET_USER_BY_USERNAME = "Select * from user where username=?";

    public static boolean validateUniqueUser(String username) {
        boolean isUnique = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_USERNAME);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                isUnique = true;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            assert statement != null;
            try {
                ConnectionPool.getConnectionPool().releaseConnection(connection);
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return isUnique;
    }

    public static boolean validateUser(String username, String pass) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            isValid = resultSet.next();
        } catch(Exception e) {
            LOGGER.error(e);
        } finally {
            assert statement != null;
            try {
                statement.close();
                ConnectionPool.getConnectionPool().releaseConnection(connection);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return isValid;
    }
}
