/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import static privateschooldb.PrivateSchoolDB.sc;

/**
 *
 * @author mapan
 */
public class Trainer {
    private int trainerId;
    private String firstName;
    private String lastName;
    private String subject;

    public Trainer(int trainerId, String firstName, String lastName, String subject) {
        this.trainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public Trainer() {
    }
    
    

    public int getTrainerId() {
        return trainerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public int checkIfTrainerIdExists() {
        SelectData selectData = new SelectData();
        ArrayList<Trainer> trainers = selectData.selectAllTrainers();
        int trainerIdInput = -1;
        boolean trainerExists = false;
        do {
            Menu.printAllTrainers();
            System.out.println("Please insert trainer's ID:");
            trainerIdInput = sc.nextInt();
            for (Trainer trainer : trainers) {
                if (trainer.getTrainerId() == trainerIdInput) {
                    trainerExists = true;
                    break;
                }
            }
            
            if (!trainerExists) {
                System.out.println("Trainer doesn't exist. Please select another ID");
            }
        } while (!trainerExists);
        return trainerIdInput;
    }
    
}
