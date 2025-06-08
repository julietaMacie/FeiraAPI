package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class VisitanteDTO {
    public record VisitanteResponse(
            Long id,
            String email,
            String nome,
            String tipoAcesso
    ) {}
    
    public record UpdateVisitante(
            @NotBlank String nome,
            @NotBlank String tipoAcesso
    ) {}
}