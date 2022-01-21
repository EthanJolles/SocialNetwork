package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IBillingAddressDao;
import com.solvd.socialNetwork.model.billing.BillingAddress;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAddressDaoImpl extends AbstractDao<BillingAddress> implements IBillingAddressDao {

    private static final Logger LOGGER = LogManager.getLogger(BillingAddressDaoImpl.class);
    private static final String CREATE_BILLING_ADDRESS = "Insert into billing_address (user_id, zip, street, city_id) VALUES (?, ?, ?, ?)";
    private static final String GET_BILLING_ADDRESS_BY_ID = "Select * from billing_address where id=?";
    private static final String UPDATE_BILLING_ADDRESS = "Update billing_address set user_id =?, zip =?, street =?" +
            ",city_id =? where id = ?";
    private static final String DELETE_BILLING_ADDRESS = "Delete from billing_address where id = ?";

    @Override
    public void create(BillingAddress billingAddress) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_BILLING_ADDRESS);
            statement.setLong(1, billingAddress.getUserId());
            statement.setLong(2, billingAddress.getZip());
            statement.setString(3, billingAddress.getStreet());
            statement.setLong(4, billingAddress.getCityId());
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
    public BillingAddress getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BillingAddress billingAddress = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_BILLING_ADDRESS_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            billingAddress = resultSetToEntity(resultSet);
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
        return billingAddress;
    }

    @Override
    public BillingAddress resultSetToEntity(ResultSet resultSet) {
        BillingAddress billingAddress = new BillingAddress();
        try {
            resultSet.next();
            billingAddress.setId(resultSet.getLong(("id")));
            billingAddress.setUserId(resultSet.getLong("profile_id"));
            billingAddress.setZip(resultSet.getInt("zip"));
            billingAddress.setStreet(resultSet.getString("street"));
            billingAddress.setCityId(resultSet.getLong("city_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return billingAddress;
    }

    @Override
    public void update(BillingAddress entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_BILLING_ADDRESS);
            statement.setLong(1,entity.getUserId());
            statement.setLong(2, entity.getZip());
            statement.setString(3, entity.getStreet());
            statement.setLong(4,entity.getCityId());
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
            statement = connection.prepareStatement(DELETE_BILLING_ADDRESS);
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
