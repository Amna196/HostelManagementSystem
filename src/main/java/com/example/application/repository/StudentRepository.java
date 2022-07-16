package com.example.application.repository;

import com.example.application.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Amna
 * @created 7/13/2022
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);
    @Query("select s.name from Student s where s.id = :studentId")
    String findNameById(Integer studentId);
}
