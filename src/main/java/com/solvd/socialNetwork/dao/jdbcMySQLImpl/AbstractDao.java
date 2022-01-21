package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IBaseDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public abstract class AbstractDao<T> implements IBaseDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    public abstract T resultSetToEntity(ResultSet resultSet);

    public void close(AutoCloseable resource) throws Exception {
        assert resource != null;
        try {
            resource.close();
        } catch (Exception e) {
            LOGGER.info(e);
        }
    }
}
