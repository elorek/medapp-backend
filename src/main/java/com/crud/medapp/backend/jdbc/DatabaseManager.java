package com.crud.medapp.backend.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private Connection connection;
    private static DatabaseManager dbManagerInstance;

    private DatabaseManager() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "ewa");
        connectionProps.put("password", "password1");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medapp_crud?serverTimezone=Europe/Warsaw",
                connectionProps);
    }

    public static DatabaseManager getInstance() throws SQLException {
        if (dbManagerInstance == null) {
            dbManagerInstance = new DatabaseManager();
        }
        return dbManagerInstance;
    }
    public Connection getConnection() {
        return connection;
    }
}
