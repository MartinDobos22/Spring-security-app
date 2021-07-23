package com.example.springsecurityapp;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "Martin Doboš"),
            new Student(2L, "Michaela Pariľáková"),
            new Student (3L, "Oliver Kudzia")
    );

    @GetMapping
    public List<Student> getAllStudent(){
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(Student student){
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(Long studentId){
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(Long studentId, @RequestBody Student student){
        System.out.println(String.format("%s %s", student, student));
    }
}
