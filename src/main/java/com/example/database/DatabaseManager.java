package com.example.database;

import com.example.config.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private static final String DB_URL = TestConfig.getDbUrl();

    static {
        migrateDatabase();
    }

    public static void migrateDatabase() {
        try {
            org.flywaydb.core.Flyway flyway = org.flywaydb.core.Flyway.configure()
                    .dataSource(DB_URL, null, null)
                    .locations("classpath:db/migration")
                    .load();
            flyway.migrate();
            logger.info("Database migration completed successfully");
        } catch (Exception e) {
            logger.error("Database migration failed", e);
            throw new RuntimeException("Database migration failed", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static TestData getTestData(String testName) {
        String sql = "SELECT * FROM test_data WHERE test_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, testName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new TestData(
                        rs.getInt("id"),
                        rs.getString("test_name"),
                        rs.getString("api_endpoint"),
                        rs.getString("request_data"),
                        rs.getString("expected_response")
                );
            }
        } catch (SQLException e) {
            logger.error("Error retrieving test data for: " + testName, e);
        }
        return null;
    }

    public static class TestData {
        private final int id;
        private final String testName;
        private final String apiEndpoint;
        private final String requestData;
        private final String expectedResponse;

        public TestData(int id, String testName, String apiEndpoint,
                        String requestData, String expectedResponse) {
            this.id = id;
            this.testName = testName;
            this.apiEndpoint = apiEndpoint;
            this.requestData = requestData;
            this.expectedResponse = expectedResponse;
        }

        // Getters
        public int getId() { return id; }
        public String getTestName() { return testName; }
        public String getApiEndpoint() { return apiEndpoint; }
        public String getRequestData() { return requestData; }
        public String getExpectedResponse() { return expectedResponse; }
    }
}