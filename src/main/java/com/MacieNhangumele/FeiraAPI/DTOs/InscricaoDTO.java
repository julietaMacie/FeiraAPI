package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class InscricaoDTO {
    public record Create(
        @NotNull Long feiraId,
        @NotNull Long visitanteId
    ) {}
    
    public record Response(
        Long id,
        Long feiraId,
        String feiraTitulo,
        Long visitanteId,
        String visitanteNome,
        LocalDateTime dataInscricao
    ) {}

    public record Update(
        String feiraId,
        LocalDateTime visitanteId
    ) {}
}