package com.MacieNhangumele.FeiraAPI.DTOs;

import com.MacieNhangumele.FeiraAPI.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthDTOs {
    public record LoginRequest(
        @NotBlank String email,
        @NotBlank String password
    ) {}
    
    public record LoginResponse(String token) {}
    
    public record RegisterRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotNull Role role
    ) {}
}