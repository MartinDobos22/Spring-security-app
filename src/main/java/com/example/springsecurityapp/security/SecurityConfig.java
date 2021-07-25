package com.example.springsecurityapp.security;

import com.example.springsecurityapp.permissionandrole.UserPermission;
import com.example.springsecurityapp.services.ApplicationUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.springsecurityapp.permissionandrole.UserRole.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailService applicationUserDetailService;


    //basic security configuration and role base authentication
    //pop up window
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                //csrf overuje token ktorý sa pošle z fe na server
//                csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
//                and().
                csrf().disable().
                authorizeRequests().
                antMatchers("/", "index", "/css/*", "/js/*").permitAll().
                                        //všetko za lomítkom /student/**
                antMatchers("/api/**").hasRole(STUDENT.name()).
                //kto môže mať práva na update, create a delete osoby z db
//                antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermisson()).
//                antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermisson()).
//                antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(UserPermission.COURSE_WRITE.getPermisson()).
//                antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name()).
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses", true).
                and().
                rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(31)).key("secured").
                and().
                //na logout pri zapnutom csrf zásadne post metoda, pri vypnutom je povolená akákoľvek http metoda
                logout().logoutUrl("/logout")
                    //zmazať tento riadok ak je csrf enable, nechať ak je disable
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me").logoutUrl("/login");
                //basic security auth
                //httpBasic();
    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            csrf().disable().
                authorizeRequests().
                antMatchers("/", "index", "/css/*", "/js/*").permitAll().
                antMatchers("/api/**").hasRole(STUDENT.name()).
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/courses", true).
                and().
                rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(31)).key("secured").
                and().
                logout().logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).
                    clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/login");

    }

    //role base authentication


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserDetailService);
        return daoAuthenticationProvider;
    }
}
