package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IOrderDao;
import com.solvd.socialNetwork.model.order.Order;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl extends AbstractDao<Order> implements IOrderDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);
    private static final String CREATE_ORDER = "Insert into order (user_id, product_id) VALUES (?, ?)";
    private static final String GET_ORDER_BY_ID = "Select * from order where id=?";
    private static final String UPDATE_ORDER = "Update order set product_id = ? where id = ?";
    private static final String DELETE_ORDER = "Delete from order where id = ?";

    @Override
    public void create(Order order) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_ORDER);
            statement.setLong(1, order.getUserId());
            statement.setLong(2, order.getProductId());
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
    public Order getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Order order = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_ORDER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            order = resultSetToEntity(resultSet);
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
        return order;
    }

    @Override
    public Order resultSetToEntity(ResultSet resultSet) {
        Order order = new Order();
        try {
            resultSet.next();
            order.setId(resultSet.getLong("id"));
            order.setUserId(resultSet.getLong("user_id"));
            order.setProductId(resultSet.getLong("product_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return order;
    }

    @Override
    public void update(Order entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_ORDER);
            statement.setLong(1, entity.getProductId());
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
            statement = connection.prepareStatement(DELETE_ORDER);
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
