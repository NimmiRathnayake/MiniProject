package model;

import java.time.LocalDate;

/**
 * Simple POJO representing a Student.
 */
public class Student {
    private int id;
    private String studentCode;
    private String name;
    private String email;
    private LocalDate dob;
    private String department;
    private Double gpa;
    private String phone;
    private String address;

    public Student() {}

    public Student(String studentCode, String name, String email,
                   LocalDate dob, String department, Double gpa,
                   String phone, String address) {
        this.studentCode = studentCode;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.department = department;
        this.gpa = gpa;
        this.phone = phone;
        this.address = address;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("Student[id=%d, code=%s, name=%s, email=%s, dept=%s, gpa=%s, dob=%s, phone=%s]",
                id, studentCode, name, email,
                department, gpa == null ? "" : gpa.toString(),
                dob == null ? "" : dob.toString(),
                phone == null ? "" : phone);
    }
}
