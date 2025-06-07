package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class FeiraDTO {
    public record CreateFeira(
        @NotBlank String titulo,
        @NotNull LocalDateTime data,
        @NotNull Long expositorId,
        @NotNull Long estandeId
    ) {}
    
    public record UpdateFeira(
        String titulo,
        LocalDateTime data,
        Long expositorId,
        Long estandeId
    ) {}
    
    public record FeiraResponse(
        Long id,
        String titulo,
        LocalDateTime data,
        Long estandeId,
        Long expositorId,
        String expositorNome
    ) {}
}