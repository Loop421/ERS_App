package com.ex.persistence;

import com.ex.persistence.exceptions.ConnectionException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionFactory implements ConnectionFactory
{
    private String url;
    private String username;
    private String password;
    private Driver registeredDriver;

    public PostgresConnectionFactory()
    {
        try {
            this.registeredDriver = new org.postgresql.Driver();
            DriverManager.registerDriver(registeredDriver);
        } catch (SQLException e) {
            throw new ConnectionException("Could not register the drive for " +
                                            this.getClass().getName(), e);
        }
    }

    public PostgresConnectionFactory(String url, String username, String password)
    {
        this();
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection newConnection() {
        try {
            return DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            throw new ConnectionException("Could not establish a connection to the database.", e);
        }
    }

    @Override
    public void destroy() {
        try {
            DriverManager.deregisterDriver(this.registeredDriver);
        } catch (SQLException e) {
            throw new ConnectionException("A problem was encountered cleaning up the database", e);
        }
    }
}
