/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.ArrayList;
import static models.Menu.printAllStudents;
import static privateschooldb.PrivateSchoolDB.sc;

/**
 *
 * @author mapan
 */
public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private float tuitionFees;

    public Student(int studentId, String firstName, String lastName, LocalDate dateOfBirth, float tuitionFees) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public Student() {
    }
    
    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public float getTuitionFees() {
        return tuitionFees;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTuitionFees(float tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    public int checkIfStudentIdExists() {
        SelectData selectData = new SelectData();
        ArrayList<Student> students = selectData.selectAllStudents();
        int studentIdInput = -1;
        boolean studentExists = false;
        do {
            printAllStudents();
            System.out.println("Please insert student's ID:");
            studentIdInput = sc.nextInt();
            for (Student student : students) {
                if (student.getStudentId() == studentIdInput) {
                    studentExists = true;
                    break;
                }
            }
            if (!studentExists) {
                System.out.println("Student doesn't exist. Please select another ID");
            }
        } while (!studentExists);
        return studentIdInput;
    }
}
