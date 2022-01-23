package com.solvd.socialNetwork.service;

import com.solvd.socialNetwork.functionalInterfaces.ICloseResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractService {

    private static final Logger LOGGER = LogManager.getLogger(AbstractService.class);

    ICloseResource closeResource = (AutoCloseable resource) -> {
        try {
            resource.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    };
}
