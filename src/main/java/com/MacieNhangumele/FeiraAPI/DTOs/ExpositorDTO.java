package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class ExpositorDTO {
    public record CreateExpositor(
        @NotBlank Long userId,
        @NotBlank String nome,
        @NotBlank String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {}
    
    public record UpdateExpositor(
        @NotBlank String nome,
        @NotBlank String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {}
    
    public record ExpositorResponse(
        Long id,
        Long userId,
        String userEmail,
        String nome,
        String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {}
}