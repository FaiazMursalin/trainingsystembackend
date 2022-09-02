package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.Batch;
import com.example.trainingsystembackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    Batch findByBatchName(String name);

}
