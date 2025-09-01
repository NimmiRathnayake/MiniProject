package dao;

import model.Student;
import java.util.List;

public interface StudentDAO {
    // Create
    void addStudent(Student student);

    // Read
    Student getStudentByCode(String studentCode);
    List<Student> getAllStudents();

    // Update
    void updateStudent(Student student);

    // Delete
    void deleteStudent(String studentCode);
}
