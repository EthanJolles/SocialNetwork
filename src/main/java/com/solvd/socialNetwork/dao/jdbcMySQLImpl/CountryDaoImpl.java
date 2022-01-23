package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.ICountryDao;
import com.solvd.socialNetwork.model.billing.Country;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDaoImpl extends AbstractDao<Country> implements ICountryDao {

    private static final Logger LOGGER = LogManager.getLogger(CountryDaoImpl.class);
    private static final String CREATE_COUNTRY = "Insert into country (country) VALUES (?)";
    private static final String GET_COUNTRY_BY_ID = "Select * from user where id=?";
    private static final String UPDATE_COUNTRY = "Update country set country = ? where id = ?";
    private static final String DELETE_COUNTRY = "Delete from country where id = ?";

    @Override
    public void create(Country country) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_COUNTRY);
            statement.setString(1, country.getCountry());
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
    public Country getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Country country = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_COUNTRY_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            country = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return country;
    }

    @Override
    public Country resultSetToEntity(ResultSet resultSet) {
        Country country = new Country();
        try {
            resultSet.next();
            country.setId(resultSet.getLong(("id")));
            country.setCountry(resultSet.getString("country"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return country;
    }

    @Override
    public void update(Country entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_COUNTRY);
            statement.setString(1, entity.getCountry());
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
            statement = connection.prepareStatement(DELETE_COUNTRY);
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
