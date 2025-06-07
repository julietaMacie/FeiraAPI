package com.MacieNhangumele.FeiraAPI.DTOs;

public record LoginResponseDTO(
    String token,
    Long userId,
    String role,
    String userType, 
    Long entityId 
) {}