/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import privateschooldb.PrivateSchoolDB;

/**
 *
 * @author mapan
 */
public class InsertData {

    public int insertStudent(String firstName, String lastName, LocalDate dateOfBirth, float tuitionFees) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO student (first_name, last_name, date_of_birth, tuition_fees) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            Date birthDate = java.sql.Date.valueOf(dateOfBirth);
            preparedStatement.setDate(3, birthDate, java.util.Calendar.getInstance());
            BigDecimal decTuitionFees = BigDecimal.valueOf(tuitionFees);
            preparedStatement.setBigDecimal(4, decTuitionFees);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertTrainer(String firstName, String lastName, String subject) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO trainer (first_name, last_name, subject) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, subject);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertAssignment(String title, String description, LocalDateTime subDateTime, float oralMark, float totalMark) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO assignment (title, description, sub_date_time, oral_mark, total_mark) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            Date subDateT = java.sql.Date.valueOf(subDateTime.toLocalDate());
            preparedStatement.setDate(3, subDateT, java.util.Calendar.getInstance());
            BigDecimal oMark = BigDecimal.valueOf(oralMark);
            preparedStatement.setBigDecimal(4, oMark);
            BigDecimal tMark = BigDecimal.valueOf(totalMark);
            preparedStatement.setBigDecimal(5, tMark);
            rowsAffected = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertCourse(String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO course (title, stream, type, start_date, end_date) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, stream);
            preparedStatement.setString(3, type);
            Date startingDate = java.sql.Date.valueOf(startDate);
            preparedStatement.setDate(4, startingDate, java.util.Calendar.getInstance());
            Date endingDate = java.sql.Date.valueOf(endDate);
            preparedStatement.setDate(5, endingDate, java.util.Calendar.getInstance());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertStudentsPerCourse(int courseId, int studentId) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO course_student (course_id, student_id) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertTrainersPerCourse(int courseId, int trainerId) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO course_trainer (course_id, trainer_id) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, trainerId);

            rowsAffected = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertAssignmentsPerCourse(int courseId, int assignmentId) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO course_assignment (course_id, assignment_id) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, assignmentId);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public int insertAssignmentsPerStudent(int studentId, int assignmentId, float studentOralMark, float studentTotalMark) {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");
            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "INSERT INTO student_assignment (student_id, assignment_id, student_oral_mark, student_total_mark) "
                    + "VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, assignmentId);
            BigDecimal studOralMark = BigDecimal.valueOf(studentOralMark);
            preparedStatement.setBigDecimal(3, studOralMark);
            BigDecimal studTotalMark = BigDecimal.valueOf(studentTotalMark);
            preparedStatement.setBigDecimal(4, studTotalMark);

            rowsAffected = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
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
        return rowsAffected;
    }

    public boolean checkIfTrainerExists(String firstName, String lastName) {
        boolean trainerExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM trainer t WHERE t.first_name = ? AND t.last_name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    trainerExists = true;
                    System.out.println("Trainer exists");
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
        return trainerExists;
    }

    public boolean checkIfStudentExists(String firstName, String lastName, LocalDate dateOfBirth) {
        boolean studentExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM student s WHERE s.first_name = ? "
                    + "AND s.last_name = ? AND s.date_of_birth = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            Date birthDate = java.sql.Date.valueOf(dateOfBirth);
            preparedStatement.setDate(3, birthDate, java.util.Calendar.getInstance());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    studentExists = true;
                    System.out.println("Student exists");
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
        return studentExists;
    }

    public boolean checkIfAssignmentExists(String title, String description, LocalDateTime subDateTime) {
        boolean assignmentExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM assignment a WHERE a.title = ? "
                    + "AND a.description = ? AND a.sub_date_time = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            Date subDateT = java.sql.Date.valueOf(subDateTime.toLocalDate());
            preparedStatement.setDate(3, subDateT, java.util.Calendar.getInstance());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    assignmentExists = true;
                    System.out.println("Assignment exists");
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
        return assignmentExists;
    }

    public boolean checkIfCourseExists(String title, String stream, String type) {
        boolean courseExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM course c WHERE c.title = ? AND c.stream = ? AND c.type = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, stream);
            preparedStatement.setString(3, type);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    courseExists = true;
                    System.out.println("Course exists");
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
        return courseExists;
    }

    public boolean checkIfStudentPerCourseExists(int courseId, int studentId) {
        boolean studentPerCourseExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM course_student cs WHERE cs.course_id = ? AND cs.student_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    studentPerCourseExists = true;
                    System.out.println("Student per course exists");
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
        return studentPerCourseExists;
    }

    public boolean checkIfTrainerPerCourseExists(int courseId, int trainerId) {
        boolean trainerPerCourseExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM course_trainer ct WHERE ct.course_id = ? AND ct.trainer_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, trainerId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    trainerPerCourseExists = true;
                    System.out.println("Trainer per course exists");
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
        return trainerPerCourseExists;
    }

    public boolean checkIfAssignmentPerCourseExists(int courseId, int assignmentId) {
        boolean assignmentPerCourseExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM course_assignment ca WHERE ca.course_id = ? AND ca.assignment_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, assignmentId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    assignmentPerCourseExists = true;
                    System.out.println("Assignment per course exists");
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
        return assignmentPerCourseExists;
    }
    
    public boolean checkIfAssignmentPerStudentExists(int studentId, int assignmentId) {
        boolean assignmentPerStudentExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(PrivateSchoolDB.MYSQL_JDDC_DRIVER);
            System.out.println("Try connect to database");

            connection = DriverManager.getConnection(PrivateSchoolDB.DB_URL, PrivateSchoolDB.USERNAME, PrivateSchoolDB.PASSWORD);
            System.out.println("Connected to database");
            String query = "SELECT count(*) FROM student_assignment sa WHERE sa.student_id = ? AND sa.assignment_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, assignmentId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    assignmentPerStudentExists = true;
                    System.out.println("Assignment per student exists");
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
        return assignmentPerStudentExists;
    }
}
