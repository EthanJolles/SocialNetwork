package com.solvd.socialNetwork.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool = new ConnectionPool();
    private final ArrayBlockingQueue<Connection> CONNECTIONS = new ArrayBlockingQueue<>(10);

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    private ConnectionPool() {
        CredentialValues credentialValues = new CredentialValues("db.properties");
        for (int i = 0; i < CONNECTIONS.remainingCapacity(); i++) {
            Connection connection;
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(System.getenv("heroku_url"),
                        System.getenv(("heroku_user")), System.getenv("heroku_password"));
                CONNECTIONS.add(connection);
            } catch (SQLException | ClassNotFoundException e) {
                LOGGER.error(e);
            }
        }
    }

    public static ConnectionPool getConnectionPool() {
        if (connectionPool == null) {
            synchronized (ConnectionPool.class) {
                connectionPool = new ConnectionPool();
            }
        }
        return connectionPool;
    }

    @Deprecated
    private boolean isConnectionAvailable() {
        if (CONNECTIONS.isEmpty()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
            isConnectionAvailable();
        }
        return true;
    }

    public synchronized Connection getConnection() {
        while (CONNECTIONS.isEmpty()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
        Connection connection = null;
        try {
            connection = CONNECTIONS.take();
        } catch(InterruptedException e) {
            LOGGER.error(e);
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        CONNECTIONS.add(connection);
    }
}
