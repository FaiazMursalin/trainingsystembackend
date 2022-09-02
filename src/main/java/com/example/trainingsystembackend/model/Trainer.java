package com.example.trainingsystembackend.model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityScan
@EnableAutoConfiguration
@Table(name="trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainerId;

    @Column(name = "age")
    private String age;

    @Column(name = "address")
    private String address;

    @OneToOne
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trainer() {
    }

    public Trainer(long trainerId, String age, String address, User user) {
        this.trainerId = trainerId;
        this.age = age;
        this.address = address;
        this.user = user;
    }

    public Trainer(String age, String address, User user) {
        this.age = age;
        this.address = address;
        this.user = user;
    }



    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
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

    @Override
    public String toString() {
        return "Trainer{" +
                "trainerId=" + trainerId +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
