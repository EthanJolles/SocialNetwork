package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IStateDao;
import com.solvd.socialNetwork.model.billing.State;
import com.solvd.socialNetwork.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDaoImpl extends AbstractDao<State> implements IStateDao {
    private static final Logger LOGGER = LogManager.getLogger(StateDaoImpl.class);
    private static final String CREATE_STATE = "Insert into state (state, country_id) VALUES (?, ?)";
    private static final String GET_STATE_BY_ID = "Select * from state where id = ?";
    private static final String UPDATE_STATE = "Update state set state = ?, country_id = ? where id = ?";
    private static final String DELETE_STATE = "Delete from state where id = ?";

    @Override
    public void create(State state) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(CREATE_STATE);
            statement.setString(1, state.getState());
            statement.setLong(2, state.getCountryId());
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
    public State getById(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        State state = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(GET_STATE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            state = resultSetToEntity(resultSet);
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
        return state;
    }

    @Override
    public State resultSetToEntity(ResultSet resultSet) {
        State state = new State();
        try {
            resultSet.next();
            state.setId(resultSet.getLong(("id")));
            state.setState(resultSet.getString("state"));
            state.setCountryId(resultSet.getLong("country_id"));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return state;
    }

    @Override
    public void update(State entity) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool().getConnection();
            statement = connection.prepareStatement(UPDATE_STATE);
            statement.setString(1, entity.getState());
            statement.setLong(2, entity.getCountryId());
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
            statement = connection.prepareStatement(DELETE_STATE);
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
