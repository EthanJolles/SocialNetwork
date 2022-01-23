package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.ILikedPostDao;
import com.solvd.socialNetwork.model.userContent.LikedPost;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikedPostDaoImpl extends AbstractDao<LikedPost> implements ILikedPostDao {

    private static final Logger LOGGER = LogManager.getLogger(LikedPostDaoImpl.class);
    private static final String CREATE_LIKED_POST = "Insert into liked_post (name, post_id) VALUES (?, ?)";
    private static final String GET_LIKED_POST_BY_ID = "Select * from liked_post where id=?";
    private static final String UPDATE_LIKED_POST = "Update liked_post set name = ? where id = ?";
    private static final String DELETE_LIKED_POST = "Delete from liked_post where id = ?";

    @Override
    public void create(LikedPost likedPost) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_LIKED_POST);
            statement.setString(1, likedPost.getName());
            statement.setLong(2, likedPost.getPostId());
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
    public LikedPost getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LikedPost likedPost = null;

        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_LIKED_POST_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            likedPost = resultSetToEntity(resultSet);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeResource.close(statement);
            closeResource.close(resultSet);
            closeResource.close(connection);
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return likedPost;
    }

    @Override
    public LikedPost resultSetToEntity(ResultSet resultSet) {
        LikedPost likedPost = new LikedPost();
        try {
            resultSet.next();
            likedPost.setId(resultSet.getLong("id"));
            likedPost.setName(resultSet.getString("name"));
            likedPost.setPostId(resultSet.getLong("post_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return likedPost;
    }

    @Override
    public void update(LikedPost entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_LIKED_POST);
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
            statement = connection.prepareStatement(DELETE_LIKED_POST);
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
