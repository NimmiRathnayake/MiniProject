import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateStudent {
    static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";    // change if needed
    static final String PASS = "1234";    // change if needed

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                String sql = "UPDATE students SET gpa = ?, phone = ? WHERE student_code = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                // Example: update GPA and phone for student with code "S001"
                ps.setDouble(1, 3.9);            // new GPA
                ps.setString(2, "0719876543");   // new phone
                ps.setString(3, "S001");         // student_code (unique identifier)

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student updated successfully!");
                } else {
                    System.out.println("No student found with that code.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
