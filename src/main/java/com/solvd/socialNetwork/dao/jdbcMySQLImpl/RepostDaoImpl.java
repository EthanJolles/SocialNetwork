package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IRepostDao;
import com.solvd.socialNetwork.model.userContent.Repost;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepostDaoImpl extends AbstractDao<Repost> implements IRepostDao {

    private static final Logger LOGGER = LogManager.getLogger(RepostDaoImpl.class);
    private static final String CREATE_REPOST = "Insert into repost (name, post_id) VALUES (?, ?)";
    private static final String GET_REPOST_BY_ID = "Select * from repost where id=?";
    private static final String UPDATE_REPOST = "Update repost set name = ?, post_id = ? where id = ?";
    private static final String DELETE_REPOST = "Delete from repost where id = ?";

    @Override
    public void create(Repost repost) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_REPOST);
            statement.setString(1, repost.getName());
            statement.setLong(2, repost.getPostId());
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
    public Repost getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Repost repost = null;

        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_REPOST_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            repost = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return repost;
    }

    @Override
    public Repost resultSetToEntity(ResultSet resultSet) {
        Repost repost = new Repost();
        try {
            resultSet.next();
            repost.setId(resultSet.getLong("id"));
            repost.setName(resultSet.getString("name"));
            repost.setPostId(resultSet.getLong("post_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return repost;
    }

    @Override
    public void update(Repost entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_REPOST);
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getPostId());
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
            statement = connection.prepareStatement(DELETE_REPOST);
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
