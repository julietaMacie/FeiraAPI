package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public record NewExpositorDTO(
    @NotBlank Long userId,
    @NotBlank String nome,
    @NotBlank String tipo,
    String linkStandOnline,
    String numeroStandFisico
) {}