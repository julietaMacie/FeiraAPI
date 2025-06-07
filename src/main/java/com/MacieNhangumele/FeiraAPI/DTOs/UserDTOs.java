package com.MacieNhangumele.FeiraAPI.DTOs;

import com.MacieNhangumele.FeiraAPI.models.Role;

public class UserDTOs {
    public record Response(
        Long id,
        String email,
        Role role
    ) {}
}