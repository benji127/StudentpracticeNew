package com.example.studentpracticenew.repositories;

import com.example.studentpracticenew.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findStudentById (long kw);
}

