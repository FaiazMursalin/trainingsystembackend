package com.example.trainingsystembackend.repository;

import com.example.trainingsystembackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByCourseName(String name);

}
