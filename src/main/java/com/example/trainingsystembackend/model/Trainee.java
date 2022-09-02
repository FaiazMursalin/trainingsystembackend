package com.example.trainingsystembackend.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@EntityScan
@EnableAutoConfiguration
@Table(name="trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long traineeId;

    @Column(name = "age")
    private String age;

    @Column(name = "address")
    private String address;

    @OneToOne
    private User user;

    @OneToOne
    private Batch batch;

    public Trainee(){

    }

    public Trainee(long traineeId, String age, String address, User user,Batch batch) {
        this.traineeId = traineeId;
        this.age = age;
        this.address = address;
        this.user = user;
        this.batch=batch;
    }

    public Trainee(String age, String address, User user,Batch batch) {
        this.age = age;
        this.address = address;
        this.user = user;
        this.batch=batch;
    }

    public long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(long traineeId) {
        this.traineeId = traineeId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "traineeId=" + traineeId +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", batch=" + batch +
                '}';
    }
}
