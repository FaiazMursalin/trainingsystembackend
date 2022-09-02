package com.example.trainingsystembackend.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityScan
@EnableAutoConfiguration
@Table(name="batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchId;

    @Column(name = "batchName")
    private String batchName;

    @Column(name = "batchDescription")
    private String batchDescription;

    @Column(name = "batchStartDate")
    private String batchStartDate;

    @Column(name = "batchEndDate")
    private String batchEndDate;

//    @OneToMany
//    private List<Course> course;

    public Batch() {
    }

    public Batch(String batchName, String batchDescription, String batchStartDate, String batchEndDate) {
        this.batchName = batchName;
        this.batchDescription = batchDescription;
        this.batchStartDate = batchStartDate;
        this.batchEndDate = batchEndDate;
    }

    public Batch(long batchId, String batchName, String batchDescription, String batchStartDate, String batchEndDate) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.batchDescription = batchDescription;
        this.batchStartDate = batchStartDate;
        this.batchEndDate = batchEndDate;
    }

//    public Batch(long batchId, String batchName, String batchDescription, String batchStartDate, String batchEndDate, List<Course> course) {
//        this.batchId = batchId;
//        this.batchName = batchName;
//        this.batchDescription = batchDescription;
//        this.batchStartDate = batchStartDate;
//        this.batchEndDate = batchEndDate;
////        this.course = course;
//    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }

    public String getBatchStartDate() {
        return batchStartDate;
    }

    public void setBatchStartDate(String batchStartDate) {
        this.batchStartDate = batchStartDate;
    }

    public String getBatchEndDate() {
        return batchEndDate;
    }

    public void setBatchEndDate(String batchEndDate) {
        this.batchEndDate = batchEndDate;
    }

//    public List<Course> getCourse() {
//        return course;
//    }
//
//    public void setCourse(List<Course> course) {
//        this.course = course;
//    }
}
