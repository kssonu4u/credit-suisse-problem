package com.mysolution.app.db;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConnection {

    public DbConnection() {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            db = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Connection conn = null;
    private String db = "";
    private String user = "SA";
    private String password = "";

    public Connection getDbConnection() {

        try {
            conn = DriverManager.getConnection(db, user, password);
        } catch (
                SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }


}
