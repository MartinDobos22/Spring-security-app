package com.example.springsecurityapp.security;

import com.example.springsecurityapp.permissionandrole.UserPermission;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.springsecurityapp.permissionandrole.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //basic security configuration and role base authentication
    //pop up window
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                //csrf overuje token ktorý sa pošle z fe na server
                //csrf().disable().
                authorizeRequests().
                antMatchers("/", "index", "/css/*", "/js/*").permitAll().
                                        //všetko za lomítkom /student/**
                antMatchers("/api/**").hasRole(STUDENT.name()).
                //kto môže mať práva na update, create a delete osoby z db
//                antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermisson()).
//                antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermisson()).
//                antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermisson()).
//                antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name()).
                anyRequest().
                authenticated().
                and().
                httpBasic();
    }

    //role base authentication
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails martinDobosUser =
                User.builder()
                        .username("martin")
                        .password(passwordEncoder.encode("123"))
//                        .roles(STUDENT.name())
                        .authorities(STUDENT.grantedAuthorities())
                        .build();

                UserDetails martonixUser = User.builder()
                        .username("martonix")
                        .password(passwordEncoder.encode("1234"))
//                        .roles(ADMIN.name())s
                        .authorities(ADMIN.grantedAuthorities())
                        .build();

                UserDetails oliverUser = User.builder()
                        .username("olinux")
                        .password(passwordEncoder.encode("12345"))
//                        .roles(ADMINTRAINEE.name())
                        .authorities(ADMINTRAINEE.grantedAuthorities())
                        .build();


        return new InMemoryUserDetailsManager(
                martinDobosUser,
                martonixUser,
                oliverUser);
    }
}
