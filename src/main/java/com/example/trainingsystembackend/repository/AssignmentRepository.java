package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.Assignment;
import com.example.trainingsystembackend.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
    Assignment findByAssignmentTitle(String assignmentTitle);
    List<Assignment> findByBatch(Batch batch);

}
