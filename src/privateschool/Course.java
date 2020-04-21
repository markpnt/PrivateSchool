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
public class Course {

    private static int counter;
    private int courseCode;
    private String title;
    private String stream;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Student> myStudents;
    private ArrayList<Trainer> myTrainers;
    private ArrayList<Assignment> myAssignments;

    public Course(String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
        myStudents = new ArrayList<>();
        myTrainers = new ArrayList<>();
        myAssignments = new ArrayList<>();
        counter++;
        this.courseCode = counter;
        this.title = title;
        this.stream = stream;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return (this.title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStream() {
        return (this.stream);
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return (this.startDate);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return (this.endDate);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "Course{" + "courseCode=" + courseCode + ", title=" + title + ", stream=" + stream + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

    public ArrayList<Student> getMyStudents() {
        return myStudents;
    }

    public void setMyStudents(ArrayList<Student> myStudents) {
        this.myStudents = myStudents;
    }

    public void setMyStudents(Student myStudent) {
        this.myStudents.add(myStudent);
    }

    public ArrayList<Trainer> getMyTrainers() {
        return myTrainers;
    }

    public void setMyTrainers(Trainer myTrainer) {
        this.myTrainers.add(myTrainer);
    }

    public ArrayList<Assignment> getMyAssignments() {
        return myAssignments;
    }

    public void setMyAssignments(Assignment myAssignment) {
        this.myAssignments.add(myAssignment);
    }
    
      public void printStudentsPerCourse() {
        System.out.println(this);
        for (int i = 0; i < myStudents.size(); i++) {
            System.out.println("\t" + myStudents.get(i));
        }
    }

    public void printTrainersPerCourse() {
        System.out.println(this);
        for (int i = 0; i < myTrainers.size(); i++) {
            System.out.println("\t" + myTrainers.get(i));
        }
    }
    
      public void printAssignmentsPerCourse() {
        System.out.println(this);
        for (int i = 0; i < myAssignments.size(); i++) {
            System.out.println("\t" + myAssignments.get(i));
        }
    }
}
