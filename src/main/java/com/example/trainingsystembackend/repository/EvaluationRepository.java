package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    List<Evaluation> findDistinctByTrainee_User(User user);
    List<Evaluation> findDistinctByAssignment_Trainer_User(User user);

}