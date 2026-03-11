package com.example.studentmanagement.controllers;

import com.example.studentmanagement.entities.Student;
import com.example.studentmanagement.business.StudentService;

/**
 * Web controller for Thymeleaf-based student management UI.
 * @author Kshitij Kale (PES1UG23CS315)
 */
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            return "index";
        }
        try {
            studentService.saveStudent(student);
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("students", studentService.getAllStudents());
            return "index";
        }
        return "redirect:/";
    }
}
