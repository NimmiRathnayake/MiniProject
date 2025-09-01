package dao;

import model.Student;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_code, name, email, dob, department, gpa, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getStudentCode());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            if (student.getDob() != null) {
                ps.setDate(4, Date.valueOf(student.getDob()));
            } else {
                ps.setNull(4, Types.DATE);
            }
            ps.setString(5, student.getDepartment());
            if (student.getGpa() != null) {
                ps.setDouble(6, student.getGpa());
            } else {
                ps.setNull(6, Types.DECIMAL);
            }
            ps.setString(7, student.getPhone());
            ps.setString(8, student.getAddress());

            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentByCode(String studentCode) {
        String sql = "SELECT * FROM students WHERE student_code = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractStudent(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, dob=?, department=?, gpa=?, phone=?, address=? WHERE student_code=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            if (student.getDob() != null) {
                ps.setDate(3, Date.valueOf(student.getDob()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setString(4, student.getDepartment());
            if (student.getGpa() != null) {
                ps.setDouble(5, student.getGpa());
            } else {
                ps.setNull(5, Types.DECIMAL);
            }
            ps.setString(6, student.getPhone());
            ps.setString(7, student.getAddress());
            ps.setString(8, student.getStudentCode());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("No student found with that code.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String studentCode) {
        String sql = "DELETE FROM students WHERE student_code=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentCode);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with that code.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // helper method to convert ResultSet row -> Student object
    private Student extractStudent(ResultSet rs) throws Exception {
        Student s = new Student();
        s.setId(rs.getInt("id"));
        s.setStudentCode(rs.getString("student_code"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        if (rs.getDate("dob") != null) {
            s.setDob(rs.getDate("dob").toLocalDate());
        }
        s.setDepartment(rs.getString("department"));
        s.setGpa(rs.getDouble("gpa"));
        s.setPhone(rs.getString("phone"));
        s.setAddress(rs.getString("address"));
        return s;
    }
}
