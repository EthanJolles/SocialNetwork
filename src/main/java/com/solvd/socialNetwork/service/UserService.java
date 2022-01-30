package com.solvd.socialNetwork.service;

import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService extends AbstractService  {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private static final String GET_USER_BY_LOGIN = "Select * from user where username=? and password=?";
    private static final String GET_USER_BY_USERNAME = "Select * from user where username=?";
    private static final String GET_USER_COUNT = "Select COUNT(*) as row_count from user";

    public long countUsers() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long userCount = 0L;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_USER_COUNT);
            resultSet = statement.executeQuery();
            resultSet.next();
            userCount = resultSet.getLong("row_count");
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return userCount;
    }

    public boolean validateUniqueUser(String username) {
        boolean isUnique = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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
            closeResource.close(statement);
            closeResource.close(resultSet);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return isUnique;
    }

    public boolean validateUser(String username, String pass) {
        boolean isValid = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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
            closeResource.close(statement);
            closeResource.close(resultSet);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return isValid;
    }
}
