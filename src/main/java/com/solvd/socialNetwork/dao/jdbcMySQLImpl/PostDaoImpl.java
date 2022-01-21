package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IPostDao;
import com.solvd.socialNetwork.model.userContent.Post;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDaoImpl extends AbstractDao<Post> implements IPostDao {

    private static final Logger LOGGER = LogManager.getLogger(PostDaoImpl.class);
    private static final String CREATE_POST = "Insert into post (location, " +
            "caption, is_picture, user_id) VALUES (?, ?, ?, ?)";
    private static final String GET_POST_BY_ID = "Select * from post where id=?";
    private static final String UPDATE_POST = "Update post set caption = ? where id = ?";
    private static final String DELETE_POST = "Delete from post where id = ?";

    @Override
    public void create(Post post) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_POST);
            statement.setString(1, post.getLocation());
            statement.setString(2, post.getCaption());
            statement.setBoolean(3, post.getIsPicture());
            statement.setLong(4, post.getUserId());
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
    public Post getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Post post = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_POST_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            post = resultSetToEntity(resultSet);
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
        return post;
    }

    @Override
    public Post resultSetToEntity(ResultSet resultSet) {
        Post post = new Post();
        try {
            resultSet.next();
            post.setId(resultSet.getLong("id"));
            post.setLocation(resultSet.getString("location"));
            post.setCaption(resultSet.getString("caption"));
            post.setIsPicture(resultSet.getBoolean("is_picture"));
            post.setUserId(resultSet.getLong("user_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return post;
    }

    @Override
    public void update(Post entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_POST);
            statement.setString(1, entity.getCaption());
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
            statement = connection.prepareStatement(DELETE_POST);
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
