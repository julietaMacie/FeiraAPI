package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotNull;

public record NewInscricaoEventoDTO(
    @NotNull Long eventoId,
    @NotNull Long visitanteId
) {}