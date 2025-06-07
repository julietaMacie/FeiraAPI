package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public record NewVisitanteDTO(
    @NotBlank Long userId,
    @NotBlank String nome,
    @NotBlank String tipoAcesso
) {}