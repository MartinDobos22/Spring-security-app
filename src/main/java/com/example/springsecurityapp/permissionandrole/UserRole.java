package com.example.springsecurityapp.permissionandrole;

import com.example.springsecurityapp.permissionandrole.UserPermission;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.springsecurityapp.permissionandrole.UserPermission.*;

@Getter
public enum UserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(
            Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE)
    ),
    ADMINTRAINEE(
            Sets.newHashSet(
            COURSE_READ,
            STUDENT_READ)
    );


    private final Set<UserPermission> permissions;

    @Autowired
    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream().map(permissions -> new SimpleGrantedAuthority(permissions.getPermisson())).collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return simpleGrantedAuthorities;
    }
}
