package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class EstandeDTO {
    public record Create(
        @NotBlank String localizacao,
        @NotBlank String status,
        Long expositorId
    ) {}
    
    public record Update(
        String localizacao,
        String status,
        Long expositorId
    ) {}
    
    public record Response(
        Long id,
        String localizacao,
        String status,
        Long expositorId,
        String expositorNome
    ) {}

}