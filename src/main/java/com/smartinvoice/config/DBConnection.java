package com.smartinvoice.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smart_invoice";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            System.getenv("SMARTLEDGER_DB_PASSWORD");

    public static Connection getConnection() {

        if (PASSWORD == null || PASSWORD.isBlank()) {

            System.err.println("Database password is not configured.");
            System.err.println(
                    "Set the SMARTLEDGER_DB_PASSWORD environment variable."
            );

            return null;
        }

        try {

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("=================================");
            System.out.println(" Database Connected Successfully ");
            System.out.println("=================================");

            return connection;

        } catch (SQLException e) {

            System.err.println("Database connection failed.");
            e.printStackTrace();

            return null;
        }
    }
}