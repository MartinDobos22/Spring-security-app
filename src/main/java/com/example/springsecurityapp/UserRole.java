package com.example.springsecurityapp;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public enum UserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            UserPermission.COURSE_READ,
            UserPermission.COURSE_WRITE,
            UserPermission.STUDENT_READ,
            UserPermission.STUDENT_WRITE)
    ),
    ADMINTRAINEE(Sets.newHashSet(
            UserPermission.COURSE_READ,
          UserPermission.STUDENT_READ)
    );


    private final Set<UserPermission> permissions;

    public Set<SimpleGrantedAuthority> grantedAuthorities(){
      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream().map(permissions -> new SimpleGrantedAuthority(permissions.getPermisson())).collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return simpleGrantedAuthorities;
    }
}
