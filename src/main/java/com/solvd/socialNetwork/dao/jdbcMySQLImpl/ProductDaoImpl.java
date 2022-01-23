package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IProductDao;
import com.solvd.socialNetwork.model.order.Product;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ProductDaoImpl extends AbstractDao<Product> implements IProductDao {


    private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);
    private static final String CREATE_PRODUCT = "Insert into product (name, price, " +
            "desc, date_listed, is_out_of_stock, sku, model_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_PRODUCT_BY_ID = "Select * from product where id=?";
    private static final String UPDATE_PRODUCT_PRICE = "Update product set price = ? where id = ?";
    private static final String DELETE_PRODUCT = "Delete from product where id = ?";

    @Override
    public void create(Product product) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDesc());
            statement.setDate(4, (Date) product.getDateListed());
            statement.setBoolean(5, product.getIsOutOfStock());
            statement.setLong(6, product.getSku());
            statement.setString(7, product.getModelNumber());
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
    public Product getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            product = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return product;
    }

    @Override
    public Product resultSetToEntity(ResultSet resultSet) {
        Product product = new Product();
        try {
            resultSet.next();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setDesc(resultSet.getString("desc"));
            product.setDateListed(resultSet.getDate("date_listed"));
            product.setIsOutOfStock(resultSet.getBoolean("is_out_of_stock"));
            product.setSku(resultSet.getLong("sku"));
            product.setModelNumber(resultSet.getString("model_number"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    @Override
    public void update(Product entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_PRODUCT_PRICE);
            statement.setDouble(1, entity.getPrice());
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
            statement = connection.prepareStatement(DELETE_PRODUCT);
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
