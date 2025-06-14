package com.ecommerce.project.controller;

import com.ecommerce.project.dto.user.LoginResponse;
import com.ecommerce.project.dto.user.LoginUserDto;
import com.ecommerce.project.dto.user.RegisterUserDto;
import com.ecommerce.project.model.User;
import com.ecommerce.project.securityServices.JwtService;
import com.ecommerce.project.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {


    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()).setUser_role(authenticatedUser.getUser_role());

        return ResponseEntity.ok(loginResponse);
    }
}
