package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IFriendListDao;
import com.solvd.socialNetwork.model.userList.FriendList;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendListDaoImpl extends AbstractDao<FriendList> implements IFriendListDao {

    private static final Logger LOGGER = LogManager.getLogger(FriendListDaoImpl.class);
    private static final String CREATE_FRIEND_LIST = "Insert into friend_list (profile_id, friend_profile_id) VALUES (?, ?)";
    private static final String GET_FRIEND_LIST_BY_ID = "Select * from friend_list where id=?";
    private static final String UPDATE_FRIEND_LIST = "Update friend_list set friend_profile_id = ? where id = ?";
    private static final String DELETE_FRIEND_LIST = "Delete from friend_list where id = ?";

    @Override
    public void create(FriendList friendList) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_FRIEND_LIST);
            statement.setLong(1, friendList.getProfileId());
            statement.setLong(2, friendList.getFriendProfileId());
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
    public FriendList getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        FriendList friendList = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_FRIEND_LIST_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            friendList = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return friendList;
    }

    @Override
    public FriendList resultSetToEntity(ResultSet resultSet) {
        FriendList friendList = new FriendList();
        try {
            resultSet.next();
            friendList.setId(resultSet.getLong("id"));
            friendList.setProfileId(resultSet.getLong("profile_id"));
            friendList.setFriendProfileId(resultSet.getLong("friend_profile_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return friendList;
    }

    @Override
    public void update(FriendList entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_FRIEND_LIST);
            statement.setLong(1, entity.getFriendProfileId());
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
            statement = connection.prepareStatement(DELETE_FRIEND_LIST);
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
