/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import static privateschool.PrivateSchool.sc;

/**
 *
 * @author mapan
 */
public class Menu {

    public void runMenu() {
        System.out.println("Welcome to private School application!");
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();

        HashMap<Integer, Course> courseHashMap = new HashMap<>();
        HashMap<Integer, Student> studentHashMap = new HashMap<>();
        HashMap<Integer, Assignment> assignmentHashMap = new HashMap<>();
        HashMap<Integer, Trainer> trainerHashMap = new HashMap<>();

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("For input data press 1 for synthtic data press 2");
        int dataChoice = sc.nextInt();
        if (dataChoice == 1) {
            boolean proceed = true;
            while (proceed) {
                System.out.println("Please press: 1 to insert course \n"
                        + "or 2 for trainer \n"
                        + "or 3 for Student \n"
                        + "or 4 for assignment");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Please type the title of the course");
                        sc.nextLine();
                        String title = sc.nextLine();
                        System.out.println("Please type the stream of the course");
                        String stream = sc.nextLine();
                        System.out.println("Please insert the type of course");
                        String type = sc.nextLine();
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
                        Course course = new Course(title, stream, type, startDt, endDt);
                        courses.add(course);
                        courseHashMap.put(course.getCourseCode(), course);
                        break;
                    case 2:
                        System.out.println("Please type the trainer's first name, lastname and the subject");
                        String fTrainerName = sc.next();
                        String lTrainerName = sc.next();
                        String subject = sc.next();
                        Trainer trainer = new Trainer(fTrainerName, lTrainerName, subject);
                        trainers.add(trainer);
                        trainerHashMap.put(trainer.getTrainerCode(), trainer);
                        break;
                    case 3:
                        System.out.println("Please type the first name and last name of the student");
                        String fStudentName = sc.next();
                        String lStudentName = sc.next();
                        System.out.println("Please type the date of birth (dd-MM-yyyy) of the student");
                        String dateOfBirth = sc.next();
                        while (!dateValid(dateOfBirth)) {
                            System.out.println("Input was not valid\n Please type a valid date (dd-MM-yyyy)");
                            dateOfBirth = sc.next();
                        }
                        LocalDate birthDate = LocalDate.parse(dateOfBirth, formater);
                        System.out.println("Please type the tuition fees");
                        Float tuitionFees = sc.nextFloat();
                        while (tuitionFees < 0 || tuitionFees > 3000) {
                            System.out.println("Wrong input the tuition fees is between 0 and 3000 €");
                            tuitionFees = sc.nextFloat();
                        }
                        Student student = new Student(fStudentName, lStudentName, birthDate, tuitionFees);
                        students.add(student);
                        studentHashMap.put(student.getStudentCode(), student);
                        break;
                    case 4:
                        System.out.println("Please type the title of the assignment:");
                        sc.nextLine();
                        String aTitle = sc.nextLine();
                        System.out.println("Please type the description of the assignment: ");
                        String description = sc.nextLine();
                        System.out.println("Please type the submission date of the assignment (dd-MM-yyyy HH:mm): ");
                        String subDateTime = sc.nextLine();
                        while (!dateTimeValid(subDateTime)) {
                            System.out.println("Input was not valid (dd-MM-yyyy HH:mm)");
                            subDateTime = sc.nextLine();
                        }
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        LocalDateTime subDate = LocalDateTime.parse(subDateTime, timeFormatter);
                        System.out.println("Please type the oral mark of the assignment");
                        Float oralMark = sc.nextFloat();
                        System.out.println("Please type the total mark of the assignment");
                        Float totalMark = sc.nextFloat();
                        Assignment assignment = new Assignment(aTitle, description, subDate, oralMark, totalMark);
                        assignments.add(assignment);
                        assignmentHashMap.put(assignment.getAssignmentCode(), assignment);
                        break;
                    default:
                        System.out.println("wrong input, please try again");
                }
                System.out.println("Press \'q\' to quit from data entry or any character to continue");
                char decission = sc.next().charAt(0);
                if (decission == 'q') {
                    proceed = false;
                }
            }
        } else { //Synthetic Data
            courses = makeSynCourses();
            courseHashMap = makeSynHashCourses(courses);

            trainers = makeSynTrainers();
            trainerHashMap = makeSynHashTrainers(trainers);

            students = makeSynStudents();
            studentHashMap = makeSynHashStudents(students);

            assignments = makeSynAssignments();
            assignmentHashMap = makeSynHashAssignments(assignments);
        }
        if (courses.isEmpty()) {
            System.out.println("You have not input courses, synthetic data activated\n");
            courses = makeSynCourses();
            courseHashMap = makeSynHashCourses(courses);
        }
        if (trainers.isEmpty()) {
            System.out.println("You have not input trainers, synthetic data activated\n");
            trainers = makeSynTrainers();
            trainerHashMap = makeSynHashTrainers(trainers);
        }
        if (students.isEmpty()) {
            System.out.println("You have not input students, synthetic data activated\n");
            students = makeSynStudents();
            studentHashMap = makeSynHashStudents(students);
        }
        if (assignments.isEmpty()) {
            System.out.println("You have not input assignments, synthetic data activated\n");
            assignments = makeSynAssignments();
            assignmentHashMap = makeSynHashAssignments(assignments);
        }
        boolean proceed = true;
        while (proceed) {
            System.out.println("Please press 1 to register students in each course"
                    + "\nor 2 for trainers per course"
                    + "\nor 3 assignments per course");
            int choice = sc.nextInt();
            switch (choice) {
                //Students per course
                case 1:
                    System.out.println(courses);
                    System.out.println("Please type the course code that you want to register students");
                    int courseCode = sc.nextInt();
                    boolean validInput = courseHashMap.containsKey(courseCode);
                    while (!validInput) {
                        System.out.println("Input was not valid!!!\nPlease type the title of course");
                        courseCode = sc.nextInt();
                        validInput = courseHashMap.containsKey(courseCode);
                    }

                    System.out.println(students);
                    System.out.println("Please type the code of student that you want to register");
                    int studentId = sc.nextInt();
                    validInput = studentHashMap.containsKey(studentId);
                    while (!validInput) {
                        System.out.println("Input was not valid!!!\nPlease type the code of student");
                        studentId = sc.nextInt();
                        validInput = studentHashMap.containsKey(studentId);
                    }
                    Course courseTmp = courseHashMap.get(courseCode);
                    Student studentTmp = studentHashMap.get(studentId);
                    courseTmp.setMyStudents(studentTmp);
                    break;
                //Trainers per course
                case 2:
                    System.out.println(courses);
                    System.out.println("Please type the course 's code that you want to register trainers");
                    int courseCode2 = sc.nextInt();
                    boolean validInput2 = courseHashMap.containsKey(courseCode2);
                    while (!validInput2) {
                        System.out.println("Input was not valid!!!\nPlease type the title of course");
                        courseCode2 = sc.nextInt();
                        validInput2 = courseHashMap.containsKey(courseCode2);
                    }
                    System.out.println(trainers);
                    System.out.println("Please type the code of trainer that you want to register: ");
                    int trainerId = sc.nextInt();
                    validInput2 = trainerHashMap.containsKey(trainerId);
                    while (!validInput2) {
                        System.out.println("Input was not valid!!!\nPlease type the code of trainer");
                        trainerId = sc.nextInt();
                        validInput2 = trainerHashMap.containsKey(trainerId);
                    }
                    Course courseTmp2 = courseHashMap.get(courseCode2);
                    Trainer trainerTmp = trainerHashMap.get(trainerId);
                    courseTmp2.setMyTrainers(trainerTmp);
                    break;
                // Assignments per course
                case 3:

                    System.out.println(courses);
                    System.out.println("Please type the course 's code");
                    int courseCode3 = sc.nextInt();
                    boolean validInput3 = courseHashMap.containsKey(courseCode3);
                    while (!validInput3) {
                        System.out.println("Input was not valid!!!\nPlease type the title of course");
                        courseCode3 = sc.nextInt();
                        validInput3 = courseHashMap.containsKey(courseCode3);
                    }
                    System.out.println(assignments);
                    System.out.println("Please type the code of assignment that you want to register");
                    int assignmentId = sc.nextInt();
                    validInput3 = assignmentHashMap.containsKey(assignmentId);
                    while (!validInput3) {
                        System.out.println("Input was not valid!!!\nPlease type the code of assignment");
                        assignmentId = sc.nextInt();
                        validInput3 = assignmentHashMap.containsKey(assignmentId);
                    }
                    Course courseTmp3 = courseHashMap.get(courseCode3);
                    Assignment assignmentTmp = assignmentHashMap.get(assignmentId);
                    courseTmp3.setMyAssignments(assignmentTmp);
            }
            System.out.println("Press \'q\' to quit from registration process or any character to continue");
            char decission = sc.next().charAt(0);
            if (decission == 'q') {
                proceed = false;
            }
        }
        boolean proceedPrint = true;
        while (proceedPrint) {
            System.out.println("Please press: 1 to print all courses or 2 to print all trainers or 3 to print all Students or 4 to print all assignments \n"
                    + "or 5 to print students per course\n"
                    + "or 6 to print trainers per course\n"
                    + "or 7 to print assignments per course\n"
                    + "or 8 to print all assignments per student\n"
                    + "or 9 to print students that belong to more than one courses\n"
                    + "or 10 to print students who need to submit one or more assignments");
            int choicePrint = sc.nextInt();
            switch (choicePrint) {
                case 1:
                    System.out.println(courses);
                    break;
                case 2:
                    System.out.println(trainers);
                    break;
                case 3:
                    System.out.println(students);
                    break;
                case 4:
                    System.out.println(assignments);
                    break;
                case 5: //Print Students per course
                    for (int i = 0; i < courses.size(); i++) {
                        Course courseTmp = courses.get(i);
                        courseTmp.printStudentsPerCourse();
                    }
                    break;
                case 6: // Print trainers per course
                    for (int i = 0; i < courses.size(); i++) {
                        Course courseTmp = courses.get(i);
                        courseTmp.printTrainersPerCourse();
                    }
                    break;
                case 7: // Print assignments per course
                    for (int i = 0; i < courses.size(); i++) {
                        Course courseTmp = courses.get(i);
                        courseTmp.printAssignmentsPerCourse();
                    }
                    break;
                case 8: //Print assignments per student
                    for (int i = 0; i < courses.size(); i++) {
                        Course courseTmp = courses.get(i);
                        ArrayList<Assignment> assignments1 = courseTmp.getMyAssignments();
                        ArrayList<Student> students1 = courseTmp.getMyStudents();
                        for (int j = 0; j < students1.size(); j++) {
                            Student studentTmp = students1.get(j);
                            System.out.println(studentTmp.getLastName() + " has the following assignments: "
                                    + "\n" + assignments1
                                    + "\n for the course: " + courseTmp);
                        }
                    }
                    break;
                case 9: //Students that belong to more than one courses
                    System.out.println("A list of students that belong to more than one courses:");
                    Set<Student> store = new HashSet<>();
                    for (int i = 0; i < courses.size(); i++) {
                        Course courseTmp = courses.get(i);
                        ArrayList<Student> students2 = courseTmp.getMyStudents();
                        for (int j = 0; j < students2.size(); j++) {
                            Student studentTmp = students2.get(j);
                            if (store.add(studentTmp) == false) {
                                System.out.println(studentTmp);
                            }
                        }
                    }
                    break;
                case 10: // Students who need to submit assignment
                    System.out.println("Please type a date (dd-MM-yyyy) to see "
                            + "the students that need to submit one or more assignments this week");
                    String dayOfWeek = sc.next();
                    while (!dateValid(dayOfWeek)) {
                        System.out.println("Input was not valid (dd-MM-yyyy)");
                        dayOfWeek = sc.next();
                    }
                    LocalDate checkDate = LocalDate.parse(dayOfWeek, formater);
                    WeekFields weekFields = WeekFields.ISO;
                    int weekNumOfCheck = checkDate.get(weekFields.weekOfWeekBasedYear()); // the number of the week
                    for (int i = 0; i < courses.size(); i++) {
                        Course courseTmp = courses.get(i);
                        ArrayList<Assignment> assignments1 = courseTmp.getMyAssignments();
                        ArrayList<Student> students1 = courseTmp.getMyStudents();
                        for (int y = 0; y < assignments1.size(); y++) {
                            Assignment assignmentTmp = assignments1.get(y);
                            LocalDateTime subDate = assignmentTmp.getSubDateTime(); //Date of the assignment
                            int weekNumOfSubDate = subDate.get(weekFields.weekOfWeekBasedYear());
                        //Check if the given date and the date of assignment is in the same year and week
                            if (checkDate.getYear() == subDate.getYear() && weekNumOfCheck == weekNumOfSubDate) {
                                System.out.println("The assignenment: " + assignmentTmp.getTitle() + " "
                                        + assignmentTmp.getSubDateTime() + " need to be submitted from these students");
                                for (int j = 0; j < students1.size(); j++) {
                                    Student studentTmp = students1.get(j);
                                    System.out.println(studentTmp.getFirstName() + " " + studentTmp.getLastName());
                                }
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Wrong input, please try again");
            }
            System.out.println("Press \'q\' to quit from data menu or any character to continue to print");
            char decission = sc.next().charAt(0);
            if (decission == 'q') {
                proceedPrint = false;
            }
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
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            formater.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Course> makeSynCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        Course course = new Course("CB10", "Java", "part time", LocalDate.of(2020, 2, 17), LocalDate.of(2020, 9, 17));
        courses.add(course);
        course = new Course("CB10", "C#", "full time", LocalDate.of(2019, 2, 24), LocalDate.of(2020, 4, 20));
        courses.add(course);
        return courses;
    }

    public HashMap<Integer, Course> makeSynHashCourses(ArrayList<Course> courses) {
        HashMap<Integer, Course> courseHashMap = new HashMap<>();
        for (Course course : courses) {
            courseHashMap.put(course.getCourseCode(), course);
        }
        return courseHashMap;
    }

    public ArrayList<Trainer> makeSynTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        Trainer trainer = new Trainer("Giorgos", "Pasparakis", "C#");
        trainers.add(trainer);
        trainer = new Trainer("George", "Irakleidis", "Java");
        trainers.add(trainer);
        return trainers;
    }

    public HashMap<Integer, Trainer> makeSynHashTrainers(ArrayList<Trainer> trainers) {
        HashMap<Integer, Trainer> trainerHashMap = new HashMap<>();
        for (Trainer trainer : trainers) {
            trainerHashMap.put(trainer.getTrainerCode(), trainer);
        }
        return trainerHashMap;
    }

    public ArrayList<Student> makeSynStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student("Markos", "Pantazis", LocalDate.of(1987, 9, 21), 1000f);
        students.add(student);
        student = new Student("Panagiotis", "Souvleros", LocalDate.of(1987, 8, 7), 1500f);
        students.add(student);
        student = new Student("Nikos", "Eustathiou", LocalDate.of(1990, 4, 7), 500f);
        students.add(student);
        student = new Student("Petros", "Nikolaou", LocalDate.of(1992, 1, 29), 400f);
        students.add(student);
        student = new Student("Aggelos", "Athanasiou", LocalDate.of(1994, 11, 20), 350f);
        students.add(student);
        student = new Student("Thanos", "Skandalis", LocalDate.of(1990, 3, 15), 1350f);
        students.add(student);
        return students;
    }

    public HashMap<Integer, Student> makeSynHashStudents(ArrayList<Student> students) {
        HashMap<Integer, Student> studentHashMap = new HashMap<>();
        for (Student student : students) {
            studentHashMap.put(student.getStudentCode(), student);
        }
        return studentHashMap;
    }

    public ArrayList<Assignment> makeSynAssignments() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        Assignment assignment = new Assignment("Individual Project", "Private school", LocalDateTime.of(2020, 4, 1, 23, 59), 20f, 80f);
        assignments.add(assignment);
        assignment = new Assignment("Team Project", "Web application", LocalDateTime.of(2020, 7, 27, 23, 59), 20f, 80f);
        assignments.add(assignment);
        return assignments;
    }

    public HashMap<Integer, Assignment> makeSynHashAssignments(ArrayList<Assignment> assignments) {
        HashMap<Integer, Assignment> assignmentHashMap = new HashMap<>();
        for (Assignment assignment : assignments) {
            assignmentHashMap.put(assignment.getAssignmentCode(), assignment);
        }
        return assignmentHashMap;
    }
}
