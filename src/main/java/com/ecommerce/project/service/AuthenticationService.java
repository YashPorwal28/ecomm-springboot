package com.ecommerce.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.project.dto.user.LoginUserDto;
import com.ecommerce.project.dto.user.RegisterUserDto;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User signup (RegisterUserDto input){
        User user = new User();
               user.setEmail(input.getEmail());
    user.setFullName(input.getFullName());
    user.setPassword(passwordEncoder.encode(input.getPassword()));
    user.setUser_role(input.getUser_role().toUpperCase());
        return userRepository.save(user);

    }

    public  User authenticate (LoginUserDto input){
        User user = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new EntityNotFoundException("User nout found with giivin email"));
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(input.getEmail() , input.getPassword()));
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

}
