package com.example.trainingsystembackend.model;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@EntityScan
@EnableAutoConfiguration
@Table(name="assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long assignmentId;

    @Column(name = "assignmentTitle")
    private String assignmentTitle;

    @Column(name = "assignmentDescription")
    private String assignmentDescription;

    @Column(name = "totalMarks")
    private String totalMarks;

    @ManyToOne
    private Batch batch;

    @ManyToOne
    private Trainer trainer;

    public Assignment() {
    }

    public Assignment(long assignmentId, String assignmentTitle, String assignmentDescription, String totalMarks, Batch batch, Trainer trainer) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.assignmentDescription = assignmentDescription;
        this.totalMarks = totalMarks;
        this.batch = batch;
        this.trainer = trainer;
    }

    public Assignment(String assignmentTitle, String assignmentDescription, String totalMarks, Batch batch, Trainer trainer) {
        this.assignmentTitle = assignmentTitle;
        this.assignmentDescription = assignmentDescription;
        this.totalMarks = totalMarks;
        this.batch = batch;
        this.trainer = trainer;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", assignmentTitle='" + assignmentTitle + '\'' +
                ", assignmentDescription='" + assignmentDescription + '\'' +
                ", totalMarks='" + totalMarks + '\'' +
                ", batch=" + batch +
                ", trainer=" + trainer +
                '}';
    }
}
