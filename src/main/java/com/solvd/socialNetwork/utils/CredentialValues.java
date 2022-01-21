package com.solvd.socialNetwork.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class CredentialValues {

	private static final Logger LOGGER = LogManager.getLogger(CredentialValues.class);
	private String url;
	private String user;
	private String password;

	public CredentialValues(String fileName) {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			Properties properties = new Properties();
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException(fileName + " not found");
			}
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
//			LOGGER.info(url);
//			LOGGER.info(user);
//			LOGGER.info(password);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}