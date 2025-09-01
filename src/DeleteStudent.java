import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteStudent {
    static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";    // change if needed
    static final String PASS = "1234";    // change if needed

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                String sql = "DELETE FROM students WHERE student_code = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                // Example: delete the student with code "S001"
                ps.setString(1, "S001");

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("No student found with that code.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
