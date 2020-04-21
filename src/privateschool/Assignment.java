/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool;

import java.time.LocalDateTime;

/**
 *
 * @author mapan
 */
public class Assignment {
    
    private static int counter;
    private int assignmentCode;
    private String title;
    private String description;
    private LocalDateTime subDateTime;
    private float oralMark;
    private float totalMark;

    public Assignment(String title, String description, LocalDateTime subDateTime, float oralMark, float totalMark) {
        counter++;
        this.assignmentCode = counter;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public int getAssignmentCode() {
        return assignmentCode;
    }

    public void setAssignmentCode(int assignmentCode) {
        this.assignmentCode = assignmentCode;
    }

    public String getTitle() {
        return (this.title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSubDateTime() {
        return (this.subDateTime);
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public float getOralMark() {
        return (this.oralMark);
    }

    public void setOralMark(float oralMark) {
        this.oralMark = oralMark;
    }

    public float getTotalMark() {
        return (this.totalMark);
    }

    public void setTotalMark(float totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignmentCode=" + assignmentCode + ", title=" + title + ", description=" + description + ", subDateTime=" + subDateTime + ", oralMark=" + oralMark + ", totalMark=" + totalMark + '}';
    }
}
