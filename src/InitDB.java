import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InitDB {
    // 1) connect to server (no DB name here)
    static final String URL_ROOT = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";              // <-- change if needed
    static final String PASS = "1234";     // <-- change

    public static void main(String[] args) {
        try {
            // Optional in modern JDBC, kept here for clarity (like textbook examples)
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL_ROOT, USER, PASS);
                 Statement st = con.createStatement()) {

                // 2) create DB and use it
                st.executeUpdate("CREATE DATABASE IF NOT EXISTS studentdb");
                st.executeUpdate("USE studentdb");

                // If you want a clean start each time, uncomment the next line:
                // st.executeUpdate("DROP TABLE IF EXISTS students");

                // 3) create table if not exists
                st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS students (" +
                                "id INT AUTO_INCREMENT PRIMARY KEY," +
                                "student_code VARCHAR(20) UNIQUE," +
                                "name VARCHAR(150) NOT NULL," +
                                "email VARCHAR(150) UNIQUE," +
                                "dob DATE," +
                                "department VARCHAR(100)," +
                                "gpa DECIMAL(3,2)," +
                                "phone VARCHAR(30)," +
                                "address TEXT," +
                                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                                ")"
                );

                System.out.println("Database & table are ready.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
