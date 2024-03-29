package com.example.springsecurityapp.services;

import com.example.springsecurityapp.auth.ApplicationUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.StreamHandler;

@Service
public class ApplicationUserDetailService implements UserDetailsService {

    private final ApplicationUserDAO applicationUserDAO;

    public ApplicationUserDetailService(@Qualifier("fake") ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDAO.selectApplicationUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Username %s not found", username))
        );
    }
}
