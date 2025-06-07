package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class VisitanteDTO {

    public record Create(
        @NotBlank Long userId,
        @NotBlank String nome,
        @NotBlank String tipoAcesso
    ) {}
    
    public record Update(
        @NotBlank String nome,
        @NotBlank String tipoAcesso
    ) {}
    
    public record Response(
        Long id,
        Long userId,
        String userEmail,
        String nome,
        String tipoAcesso
    ) {}
}