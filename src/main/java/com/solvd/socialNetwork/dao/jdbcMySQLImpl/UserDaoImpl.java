package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IUserDao;
import com.solvd.socialNetwork.model.user.User;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDao<User> implements IUserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String CREATE_USER = "Insert into user (username, password) VALUES (?, ?)";
    private static final String GET_USER_BY_ID = "Select * from user where id=?";
    private static final String GET_USER_BY_LOGIN = "Select * from user where username=? and password=?";
    private static final String UPDATE_USER = "Update user set username = ?, password = ? where id = ?";
    private static final String DELETE_USER = "Delete from user where id = ?";

    @Override
    public void create(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("create " + e);
        } finally {
            closeResource.close(statement);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    @Override
    public User getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            user = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error("getById " + e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    public User getUserByLogin(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1, username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            user = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User resultSetToEntity(ResultSet resultSet) {
        User user = new User();
        try {
            resultSet.next();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            LOGGER.error("resultSetToEntity " + e);
        }
        return user;
    }

    @Override
    public void update(User entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1,entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("update " + e);
        } finally {
            closeResource.close(statement);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(DELETE_USER);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("delete " + e);
        } finally {
            closeResource.close(statement);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }
}
