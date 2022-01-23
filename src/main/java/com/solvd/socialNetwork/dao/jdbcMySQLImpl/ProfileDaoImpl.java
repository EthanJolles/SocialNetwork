package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IProfileDao;
import com.solvd.socialNetwork.model.profile.Profile;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProfileDaoImpl extends AbstractDao<Profile> implements IProfileDao {

    private static final Logger LOGGER = LogManager.getLogger(ProfileDaoImpl.class);
    private static final String CREATE_PROFILE = "Insert into profile (first_name, last_name " +
            ",middle_initial, bio, is_verified, birthday, age, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_PROFILE_BY_ID = "Select * from profile where id=?";
    private static final String UPDATE_PROFILE = "Update profile set first_name = ?, last_name = ?, middle_initial = ?" +
            ",bio = ?, is_verified = ?, birthday = ?, age = ?, user_id = ? where id = ?";
    private static final String DELETE_PROFILE = "Delete from profile where id = ?";

    @Override
    public void create(Profile profile) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_PROFILE);
            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setString(3, profile.getMiddleInitial());
            statement.setString(4, profile.getBio());
            statement.setBoolean(5, profile.getIsVerified());
            statement.setDate(6, (Date) profile.getBirthday());
            statement.setLong(7, profile.getAge());
            statement.setLong(8, profile.getUserId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            assert statement != null;
            statement.close();
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    @Override
    public Profile getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Profile profile = null;

        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_PROFILE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            profile = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return profile;
    }

    @Override
    public Profile resultSetToEntity(ResultSet resultSet) {
        Profile profile = new Profile();
        try {
            resultSet.next();
            profile.setId(resultSet.getLong("id"));
            profile.setFirstName(resultSet.getString("first_name"));
            profile.setLastName(resultSet.getString("last_name"));
            profile.setMiddleInitial(resultSet.getString("middle_initial"));
            profile.setBio(resultSet.getString("bio"));
            profile.setIsVerified(resultSet.getBoolean("is_verified"));
            profile.setBirthday(resultSet.getDate("birthday"));
            profile.setAge(resultSet.getLong("age"));
            profile.setUserId(resultSet.getLong("user_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return profile;
    }

    @Override
    public void update(Profile entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_PROFILE);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getMiddleInitial());
            statement.setString(4, entity.getBio());
            statement.setBoolean(5, entity.getIsVerified());
            statement.setDate(6, (Date) entity.getBirthday());
            statement.setLong(7,entity.getAge());
            statement.setLong(8, entity.getUserId());
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
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
            statement = connection.prepareStatement(DELETE_PROFILE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }
}
