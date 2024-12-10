package com.Arjun.journalApplication.Configuration;

import com.Arjun.journalApplication.Services.UserServiceDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        return http.authorizeHttpRequests(request->request.
                requestMatchers("/journal/**","/user/**").authenticated().
                requestMatchers("/admin/**").hasRole("ADMIN").
                anyRequest().permitAll()).
                httpBasic(Customizer.withDefaults()).
                csrf(AbstractHttpConfigurer::disable).
                build();
    }
    @Autowired
    private UserServiceDetailImpl userServiceDetail;
    @Autowired
    public void cconfigureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userServiceDetail).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
