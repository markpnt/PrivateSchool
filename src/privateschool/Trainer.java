/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschool;

/**
 *
 * @author mapan
 */
public class Trainer {
    
    private static int counter;
    private int trainerCode;
    private String firstName;
    private String lastName;
    private String subject;

    public Trainer(String firstName, String lastName, String subject) {
        counter++;
        this.trainerCode = counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public int getTrainerCode() {
        return trainerCode;
    }

    public void setTrainerCode(int trainerCode) {
        this.trainerCode = trainerCode;
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

    public String getSubject() {
        return (this.subject);
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Trainer{" + "trainerCode=" + trainerCode + ", firstName=" + firstName + ", lastName=" + lastName + ", subject=" + subject + '}';
    }
}
