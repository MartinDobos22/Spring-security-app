package com.example.springsecurityapp.permissionandrole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public enum UserPermission {
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");


    private final String permisson;

    @Autowired
    UserPermission(String permisson) {
        this.permisson = permisson;
    }
}
