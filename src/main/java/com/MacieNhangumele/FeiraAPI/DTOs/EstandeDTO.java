package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public class EstandeDTO {
    public record CreateEstande(
        @NotBlank String localizacao,
        @NotBlank String status,
        Long expositorId
    ) {}
    
    public record UpdateEstande(
        String localizacao,
        String status,
        Long expositorId
    ) {}
    
    public record EstandeResponse(
        Long id,
        String localizacao,
        String status,
        Long expositorId,
        String expositorNome
    ) {}

}