package com.example.trainingsystembackend.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@EntityScan
@EnableAutoConfiguration
@Table(name="topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long topicId;

    @Column(name = "topicName")
    private String topicName;

    @Column(name = "topicDescription")
    private String topicDescription;



    @Column(name = "topicStartTime")
    private LocalTime topicStartTime;

    @Column(name = "topicEndTime")
    private LocalTime topicEndTime;

    @Column(name = "day")
    private String day;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    private Course course;

    public Topic() {
    }

    public Topic(long topicId, String topicName, String topicDescription, LocalTime topicStartTime, LocalTime topicEndTime, Trainer trainer, Course course,String day) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.topicStartTime = topicStartTime;
        this.topicEndTime = topicEndTime;
        this.trainer = trainer;
        this.course = course;
        this.day=day;
    }

    public Topic(String topicName, String topicDescription, LocalTime topicStartTime, LocalTime topicEndTime, Trainer trainer, Course course,String day) {
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.topicStartTime = topicStartTime;
        this.topicEndTime = topicEndTime;
        this.trainer = trainer;
        this.course = course;
        this.day=day;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getTopicStartTime() {
        return topicStartTime;
    }

    public void setTopicStartTime(LocalTime topicStartTime) {
        this.topicStartTime = topicStartTime;
    }

    public LocalTime getTopicEndTime() {
        return topicEndTime;
    }

    public void setTopicEndTime(LocalTime topicEndTime) {
        this.topicEndTime = topicEndTime;
    }
}
