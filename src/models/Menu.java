/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import static privateschooldb.PrivateSchoolDB.sc;

/**
 *
 * @author mapan
 */
public class Menu {

    public void runMenu() {
        SelectData selectData = new SelectData();
        InsertData insertData = new InsertData();
        String option;
        do {
            System.out.println("1. Select all students.");
            System.out.println("2. Select all trainers.");
            System.out.println("3. Select all assignments.");
            System.out.println("4. Select all courses.");
            System.out.println("5. Select all students per course.");
            System.out.println("6. Select all trainers per course.");
            System.out.println("7. Select all assignments per course.");
            System.out.println("8. Select all assignments per course per student.");
            System.out.println("9. Select a list of students that belong to more than one courses");
            System.out.println("10. Go to insert menu");
            System.out.println("q. To quit.");

            option = sc.next();

            int courseIdInput;
            switch (option) {
                case "1":
                    printAllStudents();
                    break;
                case "2":
                    printAllTrainers();
                    break;
                case "3":
                    printAllAssignments();
                    break;
                case "4":
                    printAllCourses();
                    break;
                case "5":
                    courseIdInput = selectData.checkIfCourseIdExists();
                    System.out.println("The following students enrolled in the course:");
                    printStudentsPerCourse(courseIdInput);
                    break;
                case "6":
                    courseIdInput = selectData.checkIfCourseIdExists();

                    ArrayList<Trainer> trainerList = selectData.selectTrainersPerCourse(courseIdInput);
                    for (Trainer trainer : trainerList) {
                        System.out.println("Trainer Id: " + trainer.getTrainerId() + ", first name: " + trainer.getFirstName()
                                + ", last name: " + trainer.getLastName() + ", subject: " + trainer.getSubject() + ".");
                    }
                    break;
                case "7":
                    courseIdInput = selectData.checkIfCourseIdExists();

                    ArrayList<Assignment> assignmentList = selectData.selectAssignmentsPerCourse(courseIdInput);
                    for (Assignment assignment : assignmentList) {
                        System.out.println("Assignment Id: " + assignment.getAssignmentId() + ", title: " + assignment.getTitle()
                                + ", description: " + assignment.getDescription() + ", submission date time: "
                                + assignment.getSubDateTime() + ", max oral mark: " + assignment.getOralMark()
                                + ", max total mark: " + assignment.getTotalMark() + ". ");
                    }
                    break;
                case "8":

                    int studentIdInput = -1;
                    courseIdInput = selectData.checkIfCourseIdExists();

                    ArrayList<Student> students = selectData.selectStudentsPerCourse(courseIdInput);
                    boolean studentExists = false;
                    do {
                        printStudentsPerCourse(courseIdInput);
                        System.out.println("Please insert student's id:");
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

                    System.out.println("The student with id:" + studentIdInput + " for the course with id:" + courseIdInput
                            + " that you chose has the following assignments:");
                    selectData.selectAssignmentsPerCoursePerStudent(courseIdInput, studentIdInput);
                    break;
                case "9":
                    Map<Student, Integer> studentsMap = selectData.selectStudentsBelongToManyCourses();

                    studentsMap.forEach((student, countCourse)
                            -> System.out.println("Student Id: " + student.getStudentId() + ", first name: " + student.getFirstName()
                                    + ", last name: " + student.getLastName() + ", date of birth: " + student.getDateOfBirth()
                                    + ", tuition fees " + student.getTuitionFees() + ", has: " + countCourse + " courses.")
                    );
                    break;
                case "10":
                    String option2 = "";
                    do {
                        System.out.println("1. Insert student");
                        System.out.println("2. Insert trainer");
                        System.out.println("3. Insert assignment");
                        System.out.println("4. Insert course");
                        System.out.println("5. Insert student per course");
                        System.out.println("6. Insert trainer per course");
                        System.out.println("7. Insert assignment per course");
                        System.out.println("8. Insert assignment per student");
                        System.out.println("q. To quit.");
                        
                        option2 = sc.next();
                        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        Student student = new Student();
                        Trainer trainer = new Trainer();
                        Assignment assignment = new Assignment();

                        int courseId;
                        switch (option2) {
                            case "1":
                                String firstName = "";
                                String lastName = "";

                                LocalDate birthDate;
                                studentExists = true;
                                do {
                                    System.out.println("Please type student's first name");
                                    firstName = sc.next();
                                    System.out.println("Please type student's last name");
                                    lastName = sc.next();
                                    System.out.println("Please type student's date of birth (dd-MM-yyyy)");
                                    String dateOfBirth = sc.next();
                                    while (!dateValid(dateOfBirth)) {
                                        System.out.println("Input was not valid\n Please type a valid date (dd-MM-yyyy)");
                                        dateOfBirth = sc.next();
                                    }
                                    birthDate = LocalDate.parse(dateOfBirth, formater);
                                    studentExists = insertData.checkIfStudentExists(firstName, lastName, birthDate);
                                } while (studentExists);

                                System.out.println("Please type the tuition fees");
                                Float tuitionFees = sc.nextFloat();
                                while (tuitionFees < 0 || tuitionFees > 3000) {
                                    System.out.println("Wrong input the tuition fees is between 0 and 3000 €");
                                    tuitionFees = sc.nextFloat();
                                }

                                int rowsAffected = insertData.insertStudent(firstName, lastName, birthDate, tuitionFees);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " rows affected\n");
                                    System.out.println("You have inserted the student: " + firstName + ", " + lastName 
                                            + ", date of birth: " + birthDate + ",tuition fees:" + tuitionFees);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "2":
                                String firstTrainerName = "";
                                String lastTrainerName = "";
                                String subject = "";
                                boolean trainerExists = true;
                                do {
                                    System.out.println("Please type trainer's first name");
                                    firstTrainerName = sc.next();
                                    System.out.println("Please type trainer's last name:");
                                    lastTrainerName = sc.next();
                                    System.out.println("Please type trainer's subject");
                                    subject = sc.next();
                                    trainerExists = insertData.checkIfTrainerExists(firstTrainerName, lastTrainerName);

                                } while (trainerExists);

                                rowsAffected = insertData.insertTrainer(firstTrainerName, lastTrainerName, subject);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " row(s) affected");
                                    System.out.println("You have inserted the trainer: " +firstTrainerName + ", " 
                                            + lastTrainerName + " with the subject: " + subject);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "3":
                                String aTitle = "";
                                String description = "";
                                LocalDateTime subDate;
                                boolean assignmentExists = true;

                                do {
                                    System.out.println("Please type the title of the assignment:");
                                    sc.nextLine();
                                    aTitle = sc.nextLine();
                                    System.out.println("Please type the description of the assignment: ");
                                    description = sc.nextLine();
                                    System.out.println("Please type the submission date of the assignment (dd-MM-yyyy HH:mm:ss): ");
                                    String subDateTime = sc.nextLine();
                                    while (!dateTimeValid(subDateTime)) {
                                        System.out.println("Input was not valid (dd-MM-yyyy HH:mm:ss)");
                                        subDateTime = sc.nextLine();
                                    }
                                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                    subDate = LocalDateTime.parse(subDateTime, timeFormatter);
                                    assignmentExists = insertData.checkIfAssignmentExists(aTitle, description, subDate);
                                } while (assignmentExists);

                                System.out.println("Please type the oral mark of the assignment");
                                Float oralMark = sc.nextFloat();
                                System.out.println("Please type the total mark of the assignment");
                                Float totalMark = sc.nextFloat();

                                rowsAffected = insertData.insertAssignment(aTitle, description, subDate, oralMark, totalMark);

                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " row(s) affected");
                                    System.out.println("You have inserted the assignment: " + aTitle + "," + description 
                                            + " submission date: " + subDate + " oral mark: " + oralMark + " total mark " + totalMark);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "4":
                                String title = "";
                                String stream = "";
                                String type = "";
                                boolean courseExists = true;
                                do {
                                    System.out.println("Please type the title of the course");
                                    sc.nextLine();
                                    title = sc.nextLine();
                                    System.out.println("Please type the stream of the course");
                                    stream = sc.nextLine();
                                    System.out.println("Please insert the type of course");
                                    type = sc.nextLine();
                                    
                                    courseExists = insertData.checkIfCourseExists(title, stream, type);
                                } while (courseExists);

                                System.out.println("Please type the starting date of course (dd-MM-yyyy)");
                                String startDate = sc.nextLine();
                                while (!dateValid(startDate)) {
                                    System.out.println("Input was not valid (dd-MM-yyyy)");
                                    startDate = sc.next();
                                }
                                LocalDate startDt = LocalDate.parse(startDate, formater);
                                System.out.println("Please type the ending date of course (dd-MM-yyyy)");
                                String endDate = sc.next();
                                while (!dateValid(endDate)) {
                                    System.out.println("Input was not valid (dd-MM-yyyy)");
                                    endDate = sc.next();
                                }
                                LocalDate endDt = LocalDate.parse(endDate, formater);
                                rowsAffected = insertData.insertCourse(title, stream, type, startDt, endDt);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " row(s) affected");
                                    System.out.println("You have inserted the course: " + title + ", " + stream + ", " + type 
                                            + ", starting date: " + startDt + ", ending date: " + endDt);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "5":
                                boolean studentPerCourseExists = true;
                                courseId = -1;
                                int studentId = -1;
                                do {
                                    courseId = selectData.checkIfCourseIdExists();
                                    studentId = student.checkIfStudentIdExists();

                                    studentPerCourseExists = insertData.checkIfStudentPerCourseExists(courseId, studentId);
                                } while (studentPerCourseExists);

                                rowsAffected = insertData.insertStudentsPerCourse(courseId, studentId);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " row(s) affected");
                                    System.out.println("Student with ID: " + studentId + " enrolled in course with ID:" + courseId);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "6":
                                boolean trainerPerCourseExists = true;
                                courseId = -1;
                                int trainerId = -1;
                                do {
                                    courseId = selectData.checkIfCourseIdExists();
                                    trainerId = trainer.checkIfTrainerIdExists();

                                    trainerPerCourseExists = insertData.checkIfTrainerPerCourseExists(courseId, trainerId);
                                } while (trainerPerCourseExists);

                                rowsAffected = insertData.insertTrainersPerCourse(courseId, trainerId);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " row(s) affected");
                                    System.out.println("Trainer with ID: " + trainerId + " teach the course with ID: " + courseId);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "7": // assignment per course
                                boolean assignmentPerCourseExists = true;
                                courseId = -1;
                                int assignmentId = -1;
                                do {
                                    courseId = selectData.checkIfCourseIdExists();
                                    assignmentId = assignment.checkIfAssignmentIdExists();

                                    assignmentPerCourseExists = insertData.checkIfAssignmentPerCourseExists(courseId, assignmentId);
                                } while (assignmentPerCourseExists);

                                rowsAffected = insertData.insertAssignmentsPerCourse(courseId, assignmentId);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " row(s) affected");
                                    System.out.println("Course with ID: " + courseId + " has the assignment with ID: " + assignmentId);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "8": // assignment per student and student's marks
                                boolean assignmentPerStudentExists = true;
                                do {
                                    studentId = student.checkIfStudentIdExists();
                                    assignmentId = assignment.checkIfAssignmentIdExists();
                                    assignmentPerStudentExists = insertData.checkIfAssignmentPerStudentExists(studentId, assignmentId);
                                } while (assignmentPerStudentExists);
                                System.out.println("Please insert the student's oral mark");
                                int studOralMark = sc.nextInt();
                                System.out.println("Please insert the student's total mark");
                                int studTotalMark = sc.nextInt();
                                rowsAffected = insertData.insertAssignmentsPerStudent(studentId, assignmentId, studOralMark, studTotalMark);
                                if (rowsAffected > 0) {
                                    System.out.println(rowsAffected + " number of rows affected");
                                    System.out.println("Student with ID: " + studentId + " for the assignment with ID: " + assignmentId 
                                            + " oral mark: " + studOralMark + " total mark: " + studTotalMark);
                                } else {
                                    System.out.println("Something went wrong");
                                }
                                break;
                            case "q":
                                break;
                            default:
                                System.out.println("Wrong input");
                        }
                    } while (!option2.equals("q"));
                case "q":
                    break;
                default:
                    System.out.println("Wrong input");
            }

        } while (!option.equals("q"));
    }

    public static void printAllCourses() {
        SelectData selectData = new SelectData();
        ArrayList<Course> courseList = selectData.selectAllCourse();
        for (Course course : courseList) {
            System.out.println("Course Id: " + course.getCourseId() + ", title: " + course.getTitle()
                    + ", stream: " + course.getStream() + ", type: " + course.getType()
                    + ", starting date: " + course.getStartDate() + ", ending date: " + course.getEndDate() + ". ");
        }
    }

    public static void printAllStudents() {
        SelectData selectData = new SelectData();
        ArrayList<Student> studentList = selectData.selectAllStudents();
        for (Student student : studentList) {
            System.out.println("Student Id: " + student.getStudentId() + ", first name: " + student.getFirstName()
                    + ", last name: " + student.getLastName() + ", date of birth: " + student.getDateOfBirth()
                    + ", tuition fees " + student.getTuitionFees() + ". ");
        }
    }

    public static void printStudentsPerCourse(int courseIdInput) {
        SelectData selectData = new SelectData();
        ArrayList<Student> studentList = selectData.selectStudentsPerCourse(courseIdInput);
        for (Student student : studentList) {
            System.out.println("Student Id: " + student.getStudentId() + ", first name: " + student.getFirstName()
                    + ", last name: " + student.getLastName() + ", date of birth: " + student.getDateOfBirth()
                    + ", tuition fees: " + student.getTuitionFees() + ".");
        }
    }

    public static void printAllTrainers() {
        SelectData selectData = new SelectData();
        ArrayList<Trainer> trainerList = selectData.selectAllTrainers();
        for (Trainer trainer : trainerList) {
            System.out.println("Trainer Id: " + trainer.getTrainerId() + ", first name: " + trainer.getFirstName()
                    + ", last name: " + trainer.getLastName() + ", subject: " + trainer.getSubject() + ". ");
        }
    }

    public static void printAllAssignments() {
        SelectData selectData = new SelectData();
        ArrayList<Assignment> assignmentList = selectData.selectAllAssignments();
        for (Assignment assignment : assignmentList) {
            System.out.println("Assignment Id: " + assignment.getAssignmentId() + ", title: " + assignment.getTitle()
                    + ", description: " + assignment.getDescription() + ", submission date time: "
                    + assignment.getSubDateTime() + ", max oral mark: " + assignment.getOralMark()
                    + ", max total mark: " + assignment.getTotalMark() + ". ");
        }
    }

    public boolean dateValid(String date) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            formater.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean dateTimeValid(String date) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try {
            formater.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
