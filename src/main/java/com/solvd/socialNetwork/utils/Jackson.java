package com.solvd.socialNetwork.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Jackson {

    private static final Logger LOGGER = LogManager.getLogger(Jackson.class);
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    public static <T> T deserialize(File file, Class<T> tClass) {
        T t = null;
        try {
            t = OBJECTMAPPER.readValue(file, tClass);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return t;
    }

    public static <T> void serialize(String desiredFilename, T t) {
        File file = new File("src/main/resources/jsonObjects/" + desiredFilename + ".json");
        try {
            OBJECTMAPPER.writeValue(file, t);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
