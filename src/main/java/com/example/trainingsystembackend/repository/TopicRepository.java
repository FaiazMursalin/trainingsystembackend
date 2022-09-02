package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.Batch;
import com.example.trainingsystembackend.model.Topic;
import com.example.trainingsystembackend.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    Topic findByTopicName(String name);
    List<Topic> findByTrainer(Trainer trainer);
    List<Topic> findDistinctByTrainerAndDay(Trainer trainer,String day);

}