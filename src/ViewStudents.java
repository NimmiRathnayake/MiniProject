import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudents {
    static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";    // change if needed
    static final String PASS = "1234";    // change if needed

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                 Statement st = con.createStatement()) {

                String sql = "SELECT * FROM students";
                ResultSet rs = st.executeQuery(sql);

                System.out.println("=== Student Records ===");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String code = rs.getString("student_code");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String dept = rs.getString("department");
                    double gpa = rs.getDouble("gpa");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");

                    System.out.println(id + " | " + code + " | " + name + " | " +
                            email + " | " + dept + " | " + gpa + " | " +
                            phone + " | " + address);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
