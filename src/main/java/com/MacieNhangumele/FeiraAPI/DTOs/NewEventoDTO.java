package com.MacieNhangumele.FeiraAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record NewEventoDTO(
    @NotBlank String titulo,
    @NotNull LocalDateTime data,
    @NotNull Long expositorId
) {}