package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class ExpositorDTO {
    public record Create(
        @NotBlank Long userId,
        @NotBlank String nome,
        @NotBlank String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {}
    
    public record Update(
        @NotBlank String nome,
        @NotBlank String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {}
    
    public record Response(
        Long id,
        Long userId,
        String userEmail,
        String nome,
        String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {}
}