package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.ICommentDao;
import com.solvd.socialNetwork.model.userContent.Comment;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDaoImpl extends AbstractDao<Comment> implements ICommentDao {

    private static final Logger LOGGER = LogManager.getLogger(CommentDaoImpl.class);
    private static final String CREATE_COMMENT = "Insert into comment (contents, post_id) VALUES (?, ?)";
    private static final String GET_COMMENT_BY_ID = "Select * from comment where id=?";
    private static final String UPDATE_COMMENT = "Update comment set contents = ? where id = ?";
    private static final String DELETE_COMMENT = "Delete from comment where id = ?";

    @Override
    public void create(Comment comment) throws SQLException {
        LOGGER.info("Creating element");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_COMMENT);
            statement.setString(1, comment.getContents());
            statement.setLong(2, comment.getPostId());
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
    public Comment getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Comment comment = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_COMMENT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            comment = resultSetToEntity(resultSet);
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
        return comment;
    }

    @Override
    public Comment resultSetToEntity(ResultSet resultSet) {
        Comment comment = new Comment();
        try {
            comment.setId(resultSet.getLong("id"));
            comment.setContents(resultSet.getString("contents"));
            comment.setPostId(resultSet.getLong("post_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return comment;
    }

    @Override
    public void update(Comment entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_COMMENT);
            statement.setString(1, entity.getContents());
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
            statement = connection.prepareStatement(DELETE_COMMENT);
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
