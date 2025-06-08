package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class ExpositorDTO {
    public record ExpositorResponse(
            Long id,
            String email,
            String nome,
            String tipo,
            String linkStandOnline,
            String numeroStandFisico
    ) {}
    
    public record UpdateExpositor(
            @NotBlank String nome,
            @NotBlank String tipo,
            @NotBlank String linkStandOnline,
            @NotBlank String numeroStandFisico
    ) {}
}