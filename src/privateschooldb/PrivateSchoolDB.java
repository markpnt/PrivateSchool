/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschooldb;

import java.util.Scanner;
import models.Menu;

/**
 *
 * @author mapan
 */
public class PrivateSchoolDB {
    
    public static Scanner sc = new Scanner(System.in);
    public static final String MYSQL_JDDC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/private_school?serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "markos1234";
    
    public static void main(String[] args) {
        // TODO code application logic here
        Menu menu = new Menu();
        menu.runMenu();
    }
    
}
