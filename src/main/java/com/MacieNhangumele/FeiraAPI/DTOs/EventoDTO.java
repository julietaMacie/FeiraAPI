package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class EventoDTO {
    public record Create(
        @NotBlank String titulo,
        @NotNull LocalDateTime data,
        @NotNull Long expositorId
    ) {}
    
    public record Update(
        String titulo,
        LocalDateTime data,
        Long expositorId
    ) {}
    
    public record Response(
        Long id,
        String titulo,
        LocalDateTime data,
        Long expositorId,
        String expositorNome
    ) {}
}