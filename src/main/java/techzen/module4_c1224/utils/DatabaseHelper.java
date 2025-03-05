//package techzen.module4_c1224.utils;
//
//import java.sql.*;
//import java.util.List;
//
//public class DatabaseHelper {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management"; // Địa chỉ của DB
//    private static final String USER_NAME = "root"; // Tên người dùng
//    private static final String PASSWORD = "123456"; // Mật khẩu
//    private static Connection connection;
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                // Tải driver JDBC cho MySQL vào bộ nhớ, điều này cần thiết để có thể kết nối.
//                Class.forName("com.mysql.jdbc.Driver");
//                // Khởi tạo kết nối tới cơ sở dữ liệu với các thông tin đã cung cấp (URL, username, password).
//                connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
//            } catch (SQLException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//
//    public static void closeConnection() throws SQLException {
//        if (connection != null && !connection.isClosed()) {
//            connection.close();
//            connection = null;
//        }
//    }
//
//
//    // Execute INSERT, UPDATE, DELETE queries
//    public static int executeUpdate(String query, List<Object> params) {
//        try (Connection conn = getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            setParameters(stmt, params);
//            return stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    // Execute SELECT queries
//    public static ResultSet executeQuery(String query, List<Object> params) {
//        try {
//            Connection conn = getConnection();
//            PreparedStatement stmt = conn.prepareStatement(query);
//
//            setParameters(stmt, params);
//            return stmt.executeQuery(); // Caller must close ResultSet & Connection
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    // Helper method to set parameters dynamically
//    private static void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException {
//        if (params != null) {
//            for (int i = 0; i < params.size(); i++) {
//                stmt.setObject(i + 1, params.get(i));
//            }
//        }
//    }
//}
