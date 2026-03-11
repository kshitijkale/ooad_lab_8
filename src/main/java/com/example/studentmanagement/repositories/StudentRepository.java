package com.example.studentmanagement.repositories;

import com.example.studentmanagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for Student entity.
 * @author Kshitij Kale (PES1UG23CS315)
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByEmailIgnoreCase(String email);
    List<Student> findByCourse(String course);
}
