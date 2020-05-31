/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import privateschooldb.PrivateSchoolDB;
import static privateschooldb.PrivateSchoolDB.sc;

/**
 *
 * @author mapan
 */
public class SelectData {

    public ArrayList<Student> selectAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try to connect in database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected, Loading...");
            String query = "SELECT student_id, first_name, last_name, date_of_birth, tuition_fees FROM student";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
                BigDecimal tuitionFeeDec = resultSet.getBigDecimal("tuition_fees");
                float tuitionFees = tuitionFeeDec.floatValue();

                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);
                students.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    public ArrayList<Trainer> selectAllTrainers() {
        ArrayList<Trainer> trainers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT trainer_id, first_name, last_name, subject FROM trainer";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int trainerId = resultSet.getInt("trainer_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String subject = resultSet.getString("subject");

                Trainer trainer = new Trainer(trainerId, firstName, lastName, subject);
                trainers.add(trainer);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return trainers;
    }

    public ArrayList<Assignment> selectAllAssignments() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT assignment_id, title, description, sub_date_time, oral_mark, total_mark FROM assignment";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assignmentId = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime subDateTime = resultSet.getTimestamp("sub_date_time", java.util.Calendar.getInstance()).toLocalDateTime();
                float oralMark = resultSet.getBigDecimal("oral_mark").floatValue();
                float totalMark = resultSet.getBigDecimal("total_mark").floatValue();

                Assignment assignment = new Assignment(assignmentId, title, description, subDateTime, oralMark, totalMark);
                assignments.add(assignment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignments;
    }

    public ArrayList<Course> selectAllCourse() {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try to connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected");
            String query = "SELECT course_id, title, stream, type, start_date, end_date FROM course";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String title = resultSet.getString("title");
                String stream = resultSet.getString("stream");
                String type = resultSet.getString("type");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();

                Course course = new Course(courseId, title, stream, type, startDate, endDate);
                courses.add(course);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }

    public ArrayList<Student> selectStudentsPerCourse(int courseId) {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected");
            String query = "SELECT s.student_id, s.first_name, s.last_name, s.date_of_birth, s.tuition_fees "
                    + "FROM student s "
                    + "INNER JOIN course_student cs ON s.student_id = cs.student_id "
                    + "WHERE cs.course_id = ? "
                    + "ORDER BY cs.student_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("s.student_id");
                String firstName = resultSet.getString("s.first_name");
                String lastName = resultSet.getString("s.last_name");
                LocalDate dateOfBirth = resultSet.getDate("s.date_of_birth").toLocalDate();
                BigDecimal tuitionFeesDec = resultSet.getBigDecimal("s.tuition_fees");
                float tuitionFees = tuitionFeesDec.floatValue();
                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);
                students.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    public ArrayList<Trainer> selectTrainersPerCourse(int courseId) {
        ArrayList<Trainer> trainers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT t.trainer_id, t.first_name, t.last_name, t.subject "
                    + "FROM trainer t "
                    + "INNER JOIN course_trainer ct ON t.trainer_id = ct.trainer_id "
                    + "WHERE ct.course_id = ? "
                    + "ORDER BY ct.trainer_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int trainerId = resultSet.getInt("t.trainer_id");
                String firstName = resultSet.getString("t.first_name");
                String lastName = resultSet.getString("t.last_name");
                String subject = resultSet.getString("t.subject");
                Trainer trainer = new Trainer(trainerId, firstName, lastName, subject);
                trainers.add(trainer);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return trainers;
    }

    public ArrayList<Assignment> selectAssignmentsPerCourse(int courseId) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT a.assignment_id, a.title, a.description, a.sub_date_time, a.oral_mark, a.total_mark "
                    + "FROM assignment a "
                    + "INNER JOIN course_assignment ca ON a.assignment_id = ca.assignment_id "
                    + "WHERE ca.course_id = ? "
                    + "ORDER BY ca.assignment_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assignmentId = resultSet.getInt("a.assignment_id");
                String title = resultSet.getString("a.title");
                String description = resultSet.getString("a.description");
                LocalDateTime subDateTime = resultSet.getTimestamp("a.sub_date_time").toLocalDateTime();
                float oralMark = resultSet.getBigDecimal("a.oral_mark").floatValue();
                float totalMark = resultSet.getBigDecimal("a.total_mark").floatValue();
                Assignment assignment = new Assignment(assignmentId, title, description, subDateTime, oralMark, totalMark);
                assignments.add(assignment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignments;
    }

    public void selectAssignmentsPerCoursePerStudent(int courseId, int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT a.assignment_id, a.title, a.description, a.sub_date_time, sa.student_oral_mark, sa.student_total_mark\n"
                    + "FROM assignment a\n"
                    + "LEFT JOIN course_assignment ca ON a.assignment_id = ca.assignment_id\n"
                    + "LEFT JOIN course c ON c.course_id = ca.course_id\n"
                    + "LEFT JOIN course_student cs ON cs.course_id = c.course_id\n"
                    + "LEFT JOIN student s ON s.student_id = cs.student_id\n"
                    + "LEFT JOIN student_assignment sa ON sa.student_id = s.student_id AND sa.assignment_id = a.assignment_id\n"
                    + "WHERE c.course_id = ? AND s.student_id = ?\n"
                    + "ORDER BY a.assignment_id";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int assignmentId = resultSet.getInt("a.assignment_id");
                String title = resultSet.getString("a.title");
                String description = resultSet.getString("a.description");
                LocalDateTime subDateTime = resultSet.getTimestamp("a.sub_date_time").toLocalDateTime();
                float studentOralMark = 0;
                float studentTotalMark = 0;
                if (resultSet.getBigDecimal("sa.student_oral_mark") == null) {
                    System.out.println("Assignment Id: " + assignmentId + ", title: " + title + ", description: " + description
                            + ", submission date time: " + subDateTime + ", student oral mark: null "
                            + ", student total mark: null. ");
                } else {
                    studentOralMark = resultSet.getBigDecimal("sa.student_oral_mark").floatValue();
                    studentTotalMark = resultSet.getBigDecimal("sa.student_total_mark").floatValue();
                    System.out.println("Assignment Id: " + assignmentId + ", title: " + title + ", description: " + description
                            + ", submission date time: " + subDateTime + ", student oral mark: " + studentOralMark
                            + ", student total mark: " + studentTotalMark + ". ");
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<Student, Integer> selectStudentsBelongToManyCourses() {
        Map<Student, Integer> studentsMap = new HashMap<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected");
            String query = "SELECT s.student_id, s.first_name, s.last_name, s.date_of_birth, s.tuition_fees, "
                    + "count(cs.student_id) AS total_courses\n"
                    + "FROM student s \n"
                    + "INNER JOIN course_student cs ON s.student_id = cs.student_id\n "
                    + "GROUP BY cs.student_id \n "
                    + "HAVING count(cs.student_id) > 1 \n"
                    + "ORDER BY s.student_id";

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("s.student_id");
                String firstName = resultSet.getString("s.first_name");
                String lastName = resultSet.getString("s.last_name");
                LocalDate dateOfBirth = resultSet.getDate("s.date_of_birth").toLocalDate();
                float tuitionFees = resultSet.getBigDecimal("s.tuition_fees").floatValue();

                int countCourses = resultSet.getInt("total_courses");
                Student student = new Student(studentId, firstName, lastName, dateOfBirth, tuitionFees);
                studentsMap.put(student, countCourses);

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return studentsMap;
    }

    public int checkIfCourseIdExists() {
        SelectData selectData = new SelectData();
        ArrayList<Course> courses = selectData.selectAllCourse();
        int courseIdInput = -1;
        boolean courseExists = false;
        do {
            Menu.printAllCourses();
            System.out.println("Please insert course's ID:");
            courseIdInput = sc.nextInt();
            for (Course course : courses) {
                if (course.getCourseId() == courseIdInput) {
                    courseExists = true;
                    System.out.println("Course you chose is: " + course.getTitle() + "," + course.getStream() + ","
                            + course.getType());
                    break;
                }
            }
            if (!courseExists) {
                System.out.println("Course doesn't exist. Please select another ID");
            }
        } while (!courseExists);
        return courseIdInput;
    }
}
