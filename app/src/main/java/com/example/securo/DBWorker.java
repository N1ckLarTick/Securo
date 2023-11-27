package com.example.securo;

import java.sql.*;

public class DBWorker {
    private final String HOST = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12665097";
    private final String USERNAME = "sql12665097";
    private final String PASSWORD = "lcv47Mtjw7";

    private Connection connection;

    public DBWorker() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
