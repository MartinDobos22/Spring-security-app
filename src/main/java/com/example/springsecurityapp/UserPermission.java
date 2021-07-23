package com.example.springsecurityapp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserPermission {
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");


    private final String permisson;

}
