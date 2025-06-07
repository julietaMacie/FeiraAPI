package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class VisitanteDTO {

    public record CreateVisitante(
        @NotBlank Long userId,
        @NotBlank String nome,
        @NotBlank String tipoAcesso
    ) {}
    
    public record UpdateVisitante(
        @NotBlank String nome,
        @NotBlank String tipoAcesso
    ) {}
    
    public record VisitanteResponse(
        Long id,
        Long userId,
        String userEmail,
        String nome,
        String tipoAcesso
    ) {}
}