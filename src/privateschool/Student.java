/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author mapan
 */
public class Student {
    private static int counter;
    private int studentCode;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Float tuitionFees ;
    private ArrayList<Assignment> myAssignments;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, Float tuitionFees) {
        myAssignments = new ArrayList<>();
        counter++;
        this.studentCode = counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }
    
    public String getFirstName() {
        return (this.firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return (this.lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return (this.dateOfBirth);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Float getTuitionFees() {
        return (this.tuitionFees);
    }

    public void setTuitionFees(Float tuitionFees) {
        this.tuitionFees = tuitionFees;
    }
    
    public ArrayList<Assignment> getMyAssignments(){
        return (this.myAssignments);
    }
    public void setMyAssignments(ArrayList<Assignment> myAssignments){
        this.myAssignments = myAssignments;
    }

    @Override
    public String toString() {
        return "Student{" + "studentCode=" + studentCode + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", tuitionFees=" + tuitionFees + '}';
    }

}
