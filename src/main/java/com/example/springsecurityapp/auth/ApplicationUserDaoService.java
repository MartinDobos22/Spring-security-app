package com.example.springsecurityapp.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.springsecurityapp.permissionandrole.UserRole.*;

@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    public List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        ADMIN.grantedAuthorities(),
                        passwordEncoder.encode("heslo0"),
                        "martonix",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        ADMINTRAINEE.grantedAuthorities(),
                        passwordEncoder.encode("heslo1"),
                        "olinux",
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        STUDENT.grantedAuthorities(),
                        passwordEncoder.encode("heslo2"),
                        "mia",
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
