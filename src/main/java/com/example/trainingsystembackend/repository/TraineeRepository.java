package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.Trainee;
import com.example.trainingsystembackend.model.Trainer;
import com.example.trainingsystembackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee,Long> {
    Trainee findByUser(User user);
}
