package com.mysolution.app.service;

import com.mysolution.app.db.DbConnection;
import com.mysolution.app.model.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.List;

public class DbEventService {

    private static final Logger logger = LogManager.getLogger(DbEventService.class);

    public final void saveEvent(List<Event> eventList) {
        logger.info("Started saving event in DB");
        Connection conn = null;
        ResultSet rs = null;
        try {
            DbConnection dbConnection = new DbConnection();
            conn = dbConnection.getDbConnection();
            // Create and execute statement
            Statement stmt = conn.createStatement();
            for (Event event : eventList) {
                rs = stmt.executeQuery("INSERT INTO PUBLIC.EVENT\n" +
                        "\t(ID, TYPE, HOST, DURATION, IS_ALERT)\n" +
                        "VALUES \n" +
                        "\t('" + event.getId() + "', '" + event.getType() + "','" + event.getHost() + "'," +
                        event.getDuration() + "," + event.isAlert() + ")");
            }
            rs.close();
            stmt.close();
            logger.info("Event successfully saved in DB");
            logger.debug("Event successfully saved in DB: {}", eventList);
        } catch (
                SQLException e) {
            logger.error("SqlException has occurred {}", e.getMessage());
        } finally {
            try {
                // Close connection
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                logger.error("SqlException has occurred {}", e.getMessage());
            }
        }

    }

    public final void readEvent() {
        logger.info("Started reading event from DB");
        Connection conn = null;
        try {
            DbConnection dbConnection = new DbConnection();
            conn = dbConnection.getDbConnection();
            // Create and execute statement
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from PUBLIC.EVENT");
            while (rs.next()) {
                logger.info("Event Information is ID: " + rs.getString("ID") + " Duration: " +
                        rs.getString("DURATION") + " Alert: " + rs.getString("IS_ALERT") +
                        " Type: " + rs.getString("TYPE") + " Host: " + rs.getString("HOST"));
            }
            rs.close();
            stmt.close();
            logger.info("Event successfully read from DB");
        } catch (SQLException e) {
            logger.error("SqlException has occurred {}", e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }


    }

}
