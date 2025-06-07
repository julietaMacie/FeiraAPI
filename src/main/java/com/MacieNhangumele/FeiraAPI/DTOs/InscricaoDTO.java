package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class InscricaoDTO {
    public record Create(
        @NotNull Long eventoId,
        @NotNull Long visitanteId
    ) {}
    
    public record Response(
        Long id,
        Long eventoId,
        String eventoTitulo,
        Long visitanteId,
        String visitanteNome,
        LocalDateTime dataInscricao
    ) {}

    public record Update(
        String eventoId,
        LocalDateTime visitanteId
    ) {}
}