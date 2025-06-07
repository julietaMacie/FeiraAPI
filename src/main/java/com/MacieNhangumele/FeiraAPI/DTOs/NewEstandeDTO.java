package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;

public record NewEstandeDTO(
    @NotBlank String localizacao,
    @NotBlank String status,
    Long expositorId
) {}