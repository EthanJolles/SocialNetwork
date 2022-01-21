package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.ISavedPostDao;
import com.solvd.socialNetwork.model.userContent.SavedPost;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SavedPostDaoImpl extends AbstractDao<SavedPost> implements ISavedPostDao {
    private static final Logger LOGGER = LogManager.getLogger(SavedPostDaoImpl.class);
    private static final String CREATE_SAVED_POST = "Insert into saved_post (name, post_id) VALUES (?, ?)";
    private static final String GET_SAVED_POST_BY_ID = "Select * from saved_post where id = ?";
    private static final String UPDATE_SAVED_POST = "Update saved_post set name = ?, post_id = ? where id = ?";
    private static final String DELETE_SAVED_POST = "Delete from saved_post where id = ?";

    @Override
    public void create(SavedPost savedPost) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_SAVED_POST);
            statement.setString(1, savedPost.getName());
            statement.setLong(2, savedPost.getPostId());
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
    public SavedPost getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SavedPost savedPost = null;

        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_SAVED_POST_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            savedPost = resultSetToEntity(resultSet);
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
        return savedPost;
    }

    @Override
    public SavedPost resultSetToEntity(ResultSet resultSet) {
        SavedPost savedPost = new SavedPost();
        try {
            resultSet.next();
            savedPost.setId(resultSet.getLong("id"));
            savedPost.setName(resultSet.getString("name"));
            savedPost.setPostId(resultSet.getLong("post_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return savedPost;
    }

    @Override
    public void update(SavedPost entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_SAVED_POST);
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getPostId());
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
            statement = connection.prepareStatement(DELETE_SAVED_POST);
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
