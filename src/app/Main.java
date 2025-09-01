package app;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAOImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Code");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    private static void addStudent() {
        System.out.println("Enter student code: ");
        String code = scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter date of birth (yyyy-mm-dd or leave blank): ");
        String dobStr = scanner.nextLine();
        LocalDate dob = dobStr.isBlank() ? null : LocalDate.parse(dobStr);
        System.out.println("Enter department: ");
        String dept = scanner.nextLine();
        System.out.println("Enter GPA (or leave blank): ");
        String gpaStr = scanner.nextLine();
        Double gpa = gpaStr.isBlank() ? null : Double.parseDouble(gpaStr);
        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter address: ");
        String addr = scanner.nextLine();

        Student s = new Student(code, name, email, dob, dept, gpa, phone, addr);
        studentDAO.addStudent(s);
    }

    private static void viewAllStudents() {
        List<Student> list = studentDAO.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void searchStudent() {
        System.out.println("Enter student code: ");
        String code = scanner.nextLine();
        Student s = studentDAO.getStudentByCode(code);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.println("Enter student code to update: ");
        String code = scanner.nextLine();
        Student s = studentDAO.getStudentByCode(code);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Updating student: " + s);

        System.out.println("Enter new name (leave blank to keep): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) s.setName(name);

        System.out.println("Enter new email (leave blank to keep): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) s.setEmail(email);

        System.out.println("Enter new dob (yyyy-mm-dd or leave blank): ");
        String dobStr = scanner.nextLine();
        if (!dobStr.isBlank()) s.setDob(LocalDate.parse(dobStr));

        System.out.println("Enter new department (leave blank to keep): ");
        String dept = scanner.nextLine();
        if (!dept.isBlank()) s.setDepartment(dept);

        System.out.println("Enter new GPA (leave blank to keep): ");
        String gpaStr = scanner.nextLine();
        if (!gpaStr.isBlank()) s.setGpa(Double.parseDouble(gpaStr));

        System.out.println("Enter new phone (leave blank to keep): ");
        String phone = scanner.nextLine();
        if (!phone.isBlank()) s.setPhone(phone);

        System.out.println("Enter new address (leave blank to keep): ");
        String addr = scanner.nextLine();
        if (!addr.isBlank()) s.setAddress(addr);

        studentDAO.updateStudent(s);
    }

    private static void deleteStudent() {
        System.out.println("Enter student code to delete: ");
        String code = scanner.nextLine();
        studentDAO.deleteStudent(code);
    }
}
