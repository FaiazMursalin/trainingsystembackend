package com.example.trainingsystembackend.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@EntityScan
@EnableAutoConfiguration
@Table(name="evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long evaluationId;

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private Trainee trainee;

    @Column(name = "obtainedMarks")
    private int obtainedMarks;

    @Column(name = "answers")
    private String answers;

    public Evaluation() {
    }

    public Evaluation(long evaluationId, Assignment assignment, Trainee trainee, int obtainedMarks, String answers) {
        this.evaluationId = evaluationId;
        this.assignment = assignment;
        this.trainee = trainee;
        this.obtainedMarks = obtainedMarks;
        this.answers = answers;
    }

    public Evaluation(Assignment assignment, Trainee trainee, int obtainedMarks, String answers) {
        this.assignment = assignment;
        this.trainee = trainee;
        this.obtainedMarks = obtainedMarks;
        this.answers = answers;
    }

    public long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", assignment=" + assignment +
                ", trainee=" + trainee +
                ", obtainedMarks=" + obtainedMarks +
                ", answers='" + answers + '\'' +
                '}';
    }
    //
//    @Column(name = "assignmentTitle")
//    private String assignmentTitle;
}
