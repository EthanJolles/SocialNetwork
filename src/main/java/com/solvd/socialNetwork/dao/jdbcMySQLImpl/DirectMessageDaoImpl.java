package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IDirectMessageDao;
import com.solvd.socialNetwork.model.userContent.DirectMessage;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DirectMessageDaoImpl extends AbstractDao<DirectMessage> implements IDirectMessageDao {

    private static final Logger LOGGER = LogManager.getLogger(DirectMessageDaoImpl.class);
    private static final String CREATE_DIRECT_MESSAGE = "Insert into direct_message (message, " +
            "user_id, recipient_id, date_sent) VALUES (?, ?, ?, ?)";
    private static final String GET_DIRECT_MESSAGE_BY_ID = "Select * from direct_message where id=?";
    private static final String UPDATE_DIRECT_MESSAGE = "Update direct_message set message = ? where id = ?";
    private static final String DELETE_DIRECT_MESSAGE = "Delete from direct_message where id = ?";

    @Override
    public void create(DirectMessage directMessage) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_DIRECT_MESSAGE);
            statement.setString(1, directMessage.getMessage());
            statement.setLong(2, directMessage.getUserId());
            statement.setLong(3, directMessage.getRecipientId());
            statement.setDate(4, (Date) directMessage.getDateSent());
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
    public DirectMessage getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DirectMessage directMessage = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_DIRECT_MESSAGE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            directMessage = resultSetToEntity(resultSet);
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
        return directMessage;
    }

    @Override
    public DirectMessage resultSetToEntity(ResultSet resultSet) {
        DirectMessage directMessage = new DirectMessage();
        try {
            directMessage.setId(resultSet.getLong("id"));
            directMessage.setMessage(resultSet.getString("message"));
            directMessage.setUserId(resultSet.getLong("user_id"));
            directMessage.setRecipientId(resultSet.getLong("recipient_id"));
            directMessage.setDateSent(resultSet.getDate("user_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return directMessage;
    }

    @Override
    public void update(DirectMessage entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_DIRECT_MESSAGE);
            statement.setString(1, entity.getMessage());
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
            statement = connection.prepareStatement(DELETE_DIRECT_MESSAGE);
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
