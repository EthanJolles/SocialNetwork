package com.solvd.socialNetwork.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractService {

    private static final Logger LOGGER = LogManager.getLogger(AbstractService.class);

    public void close(AutoCloseable resource) throws Exception {
        assert resource != null;
        try {
            resource.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
