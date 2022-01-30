package com.solvd.socialNetwork.dao.jdbcMySQLImpl;

import com.solvd.socialNetwork.dao.interfaces.IBaseDao;
import com.solvd.socialNetwork.functionalInterfaces.ICloseResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public abstract class AbstractDao<T> implements IBaseDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    public abstract T resultSetToEntity(ResultSet resultSet);

    ICloseResource closeResource = (AutoCloseable resource) -> {
         try {
             resource.close();
         } catch (Exception e) {
             LOGGER.error(e);
         }
    };

    @Deprecated
    public void close(AutoCloseable resource) {
        try {
            resource.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
