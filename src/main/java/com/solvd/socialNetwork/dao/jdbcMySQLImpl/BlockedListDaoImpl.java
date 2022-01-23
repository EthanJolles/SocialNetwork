package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IBlockedListDao;
import com.solvd.socialNetwork.model.userList.BlockedList;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BlockedListDaoImpl extends AbstractDao<BlockedList> implements IBlockedListDao {

    private static final Logger LOGGER = LogManager.getLogger(BlockedListDaoImpl.class);
    private static final String CREATE_BLOCKED_LIST = "Insert into blocked_list (profile_id, " +
            "blocked_profile_id) VALUES (?, ?)";
    private static final String GET_BLOCKED_LIST_BY_ID = "Select * from blocked_list where id=?";
    private static final String UPDATE_BLOCKED_LIST = "Update blocked_list set blocked_profile_id = ? where id = ?";
    private static final String DELETE_BLOCKED_LIST = "Delete from blocked_list where id = ?";

    @Override
    public void create(BlockedList blockedList) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_BLOCKED_LIST);
            statement.setLong(1, blockedList.getProfileId());
            statement.setLong(2, blockedList.getBlockedProfileId());
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
    public BlockedList getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        BlockedList blockedList = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_BLOCKED_LIST_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            blockedList = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return blockedList;
    }

    @Override
    public BlockedList resultSetToEntity(ResultSet resultSet) {
        BlockedList blockedList = new BlockedList();
        try {
            resultSet.next();
            blockedList.setId(resultSet.getLong("id"));
            blockedList.setProfileId(resultSet.getLong("profile_id"));
            blockedList.setBlockedProfileId(resultSet.getLong("blocked_profile_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return blockedList;
    }

    @Override
    public void update(BlockedList entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_BLOCKED_LIST);
            statement.setLong(1, entity.getBlockedProfileId());
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
            statement = connection.prepareStatement(DELETE_BLOCKED_LIST);
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
