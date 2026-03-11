package com.example.studentmanagement.business;

import com.example.studentmanagement.entities.Student;
import com.example.studentmanagement.repositories.StudentRepository;

/**
 * Service layer for student business logic.
 * @author Kshitij Kale (PES1UG23CS315)
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        String normalizedEmail = student.getEmail().trim().toLowerCase();
        if (studentRepository.findByEmailIgnoreCase(normalizedEmail).isPresent()) {
            throw new IllegalStateException("Email already registered");
        }
        student.setName(student.getName().trim());
        student.setEmail(normalizedEmail);
        student.setCourse(student.getCourse().trim());
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }
}
