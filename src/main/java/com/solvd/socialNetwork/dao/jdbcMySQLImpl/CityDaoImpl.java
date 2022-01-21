package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.ICityDao;
import com.solvd.socialNetwork.model.billing.City;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl extends AbstractDao<City> implements ICityDao {

    private static final Logger LOGGER = LogManager.getLogger(CityDaoImpl.class);
    private static final String CREATE_CITY = "Insert into city (city, state_id) VALUES (?, ?)";
    private static final String GET_CITY_BY_ID = "Select * from city where id=?";
    private static final String UPDATE_CITY = "Update city set city = ? where id = ?";
    private static final String DELETE_CITY = "Delete from city where id = ?";

    @Override
    public void create(City city) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_CITY);
            statement.setString(1, city.getCity());
            statement.setLong(2, city.getStateId());
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
    public City getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        City city = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_CITY_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            city = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                close(statement);
                close(resultSet);
            } catch (Exception e) {
                LOGGER.error(e);
            }
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return city;
    }

    @Override
    public City resultSetToEntity(ResultSet resultSet) {
        City city = new City();
        try {
            resultSet.next();
            city.setId(resultSet.getLong(("id")));
            city.setCity(resultSet.getString("city"));
            city.setStateId(resultSet.getLong("state_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return city;
    }

    @Override
    public void update(City entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_CITY);
            statement.setString(1, entity.getCity());
            statement.setLong(2,entity.getStateId());
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
    public void delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(DELETE_CITY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            assert statement != null;
            statement.close();
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }
}
