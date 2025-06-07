package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.*;
import com.MacieNhangumele.FeiraAPI.models.*;
import com.MacieNhangumele.FeiraAPI.services.*;
import com.MacieNhangumele.FeiraAPI.configuration.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenService tokenService,
            PasswordEncoder passwordEncoder) {
        
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.LoginResponse> login(
            @RequestBody @Valid AuthDTOs.LoginRequest request) {
        
        var authToken = new UsernamePasswordAuthenticationToken(
            request.email(), 
            request.password());
        
        var authentication = authenticationManager.authenticate(authToken);
        var user = (User) authentication.getPrincipal();
        
        return ResponseEntity.ok(new AuthDTOs.LoginResponse(
            tokenService.generateToken(user)
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody @Valid AuthDTOs.RegisterRequest request) {
        
        if (userService.existsByEmail(request.email())) {
            return ResponseEntity.badRequest().build();
        }

        User newUser = User.builder()
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(request.role())
            .build();
        
        userService.save(newUser);
        return ResponseEntity.ok().build();
    }
}