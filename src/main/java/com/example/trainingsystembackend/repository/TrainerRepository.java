package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.Trainer;
import com.example.trainingsystembackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    Trainer findByUser(User user);

}
