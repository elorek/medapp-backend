package com.crud.medapp.backend.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManagerTest {
    @Test
    public void testConnection() throws SQLException {
        //Given
        //When
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        //Then
        Assert.assertNotNull(databaseManager.getConnection());
    }

    @Test
    public void testSelectAppointments() throws SQLException {
        //Given
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        //When
        String sqlQuery = "SELECT * FROM APPOINTMENTS";
        Statement statement = databaseManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        //Then
        int counter = 0;
        while(resultSet.next()) {
            System.out.println(resultSet.getInt("id") + ". " + resultSet.getInt("patient_id")
            + ", " + resultSet.getInt("doctor_id") + ", " + resultSet.getTimestamp("time")
            + ", " + resultSet.getString("place") + ", " + resultSet.getInt("room"));
        }
        resultSet.close();
        statement.close();
        Assert.assertEquals(33, counter);
    }

    @Test
    public void testSelectDoctors() throws SQLException {
        //Given
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        //When
        String sqlQuery = "SELECT * FROM DOCTORS";
        Statement statement = databaseManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        //Then
        int counter = 0;
        while(resultSet.next()) {
            System.out.println(resultSet.getInt("id") + ". " + resultSet.getString("name")
                    + ", " + resultSet.getString("last_name") + ", " + resultSet.getString("specialization"));
        }
        resultSet.close();
        statement.close();
        Assert.assertEquals(13, counter);
    }

    @Test
    public void testSelectPatients() throws SQLException {
        //Given
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        //When
        String sqlQuery = "SELECT * FROM PATIENTS";
        Statement statement = databaseManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        //Then
        int counter = 0;
        while(resultSet.next()) {
            System.out.println(resultSet.getInt("id") + ". " + resultSet.getString("name")
                    + ", " + resultSet.getString("last_name") + ", " + resultSet.getString("pesel")
                    + ", " + resultSet.getString("adress"));
        }
        resultSet.close();
        statement.close();
        Assert.assertEquals(5, counter);
    }
}
