package com.example.springsecurityapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/students")
public class RestController {


    private static final List<Student> STUDENTS = Arrays.asList(
      new Student(1L, "Martin Doboš"),
      new Student(2L, "Michaela Pariľáková"),
      new Student (3L, "Oliver Kudzia")
    );

    @GetMapping("/id/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId){
        return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId())).findFirst().orElseThrow(
                () -> new IllegalStateException("Student doesnt exist with " + studentId + " id ")
        );
    }

}
