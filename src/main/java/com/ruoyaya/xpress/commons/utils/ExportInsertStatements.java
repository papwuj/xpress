package com.ruoyaya.xpress.commons.utils;

import java.sql.*;
import java.text.SimpleDateFormat;

public class ExportInsertStatements {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/wordpress?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement tableStatement = conn.createStatement();
            ResultSet rs = tableStatement.executeQuery("SHOW TABLES");
            while (rs.next()) {
                String tableName = rs.getString(1);
                System.out.println("Table: " + tableName);

                // Use a separate Statement to avoid closing the ResultSet of "SHOW TABLES"
                try (Statement dataStatement = conn.createStatement()) {
                    ResultSet data = dataStatement.executeQuery("SELECT * FROM " + tableName);
                    ResultSetMetaData rsmd = data.getMetaData();
                    int columnCount = rsmd.getColumnCount();

                    while (data.next()) {
                        StringBuilder insertStatement = new StringBuilder("INSERT INTO " + tableName + " (");
                        for (int i = 1; i <= columnCount; i++) {
                            insertStatement.append(rsmd.getColumnName(i));
                            if (i < columnCount) {
                                insertStatement.append(", ");
                            }
                        }
                        insertStatement.append(") VALUES (");
                        for (int i = 1; i <= columnCount; i++) {
                            // Handle different types as per the previous example...
                            insertStatement.append(formatValueBasedOnType(data, rsmd, i));
                            if (i < columnCount) {
                                insertStatement.append(", ");
                            }
                        }
                        insertStatement.append(");");
                        System.out.println(insertStatement.toString());
                    }
                    // No need to close the ResultSet here as it's enclosed in try-with-resources
                }
            }
            tableStatement.close(); // Close the table statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String formatValueBasedOnType(ResultSet data, ResultSetMetaData rsmd, int columnIndex) throws SQLException {
        int columnType = rsmd.getColumnType(columnIndex);
        switch (columnType) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR: {
                Object value = data.getObject(columnIndex);
                return value != null ? "'" + escapeSQL(value.toString()) + "'" : "NULL";
            }
            case Types.DATE:
            case Types.TIMESTAMP: {
                String dateString = data.getString(columnIndex);
                if (dateString != null && !dateString.startsWith("0000-00-00")) {
                    Object value = data.getObject(columnIndex);
                    if (value instanceof Date) {
                        String timestamp = timestampFormat.format(value);
                        return "STR_TO_DATE('" + timestamp + "', '%Y-%m-%d %H:%i:%s')";
                    } else if (value instanceof Time) {
                        String formattedTime = timeFormat.format(value);
                        return "STR_TO_DATE('" + formattedTime + "', '%H:%i:%s')";
                    } else if (value instanceof Timestamp) {
                        String timestamp = timestampFormat.format(value);
                        return "STR_TO_DATE('" + timestamp + "', '%Y-%m-%d %H:%i:%s')";
                    } else {
                        return "'" + value + "'";
                    }
                } else {
                    return "NULL";
                }
            }
            case Types.INTEGER:
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.BIGINT:
            case Types.FLOAT:
            case Types.DOUBLE:
            case Types.DECIMAL:
            case Types.NUMERIC: {
                Object value = data.getObject(columnIndex);
                return value != null ? value.toString() : "NULL";
            }
            case Types.BOOLEAN: {
                Object value = data.getObject(columnIndex);
                return value != null ? (Boolean) value ? "1" : "0" : "NULL";
            }
            default:
                return data.getObject(columnIndex) != null ? escapeSQL(data.getObject(columnIndex).toString()) : "NULL";
        }
    }
    private static String escapeSQL(String input) {
        return input.replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\0", "\\0")
                .replace("\b", "\\b")
                .replace("\t", "\\t");
    }
}
