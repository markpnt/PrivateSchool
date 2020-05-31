/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static privateschooldb.PrivateSchoolDB.sc;

/**
 *
 * @author mapan
 */
public class Assignment {
    private int assignmentId;
    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private float oralMark;
    private float totalMark;

    public Assignment(int assignmentId, String title, String description, LocalDateTime subDateTime, float oralMark, float totalMark) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public Assignment() {
    }
    
    public int getAssignmentId() {
        return assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public float getOralMark() {
        return oralMark;
    }

    public float getTotalMark() {
        return totalMark;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public void setOralMark(float oralMark) {
        this.oralMark = oralMark;
    }

    public void setTotalMark(float totalMark) {
        this.totalMark = totalMark;
    }
    
    public int checkIfAssignmentIdExists() {
        SelectData selectData = new SelectData();
        ArrayList<Assignment> assignments = selectData.selectAllAssignments();
        int assignmentIdInput = -1;
        boolean assignmentExists = false;
        do {
            Menu.printAllAssignments();
            System.out.println("Please insert assignment's ID:");
            assignmentIdInput = sc.nextInt();
            
            for (Assignment assignment : assignments) {
                if (assignment.getAssignmentId() == assignmentIdInput) {
                    assignmentExists = true;
                    System.out.println("Assignment you chose is: " + assignment.getTitle() + "," + assignment.getDescription()+ "," 
                            + assignment.getSubDateTime() );
                    break;
                }
            }
            if (!assignmentExists) {
                System.out.println("Assignment doesn't exist. Please select another ID");
            }
        } while (!assignmentExists);
        return assignmentIdInput;
    }
}
