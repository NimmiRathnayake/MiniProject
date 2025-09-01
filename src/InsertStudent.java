import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertStudent {
    static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";        // change if needed
    static final String PASS = "1234";        // change if needed

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                String sql = "INSERT INTO students (student_code, name, email, department, gpa, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);

                // Example student data
                ps.setString(1, "S001");
                ps.setString(2, "Nimal Perera");
                ps.setString(3, "nimal@example.com");
                ps.setString(4, "IT");
                ps.setDouble(5, 3.5);
                ps.setString(6, "0771234567");
                ps.setString(7, "Colombo");

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student inserted successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
