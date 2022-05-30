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

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));
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
