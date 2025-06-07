package com.MacieNhangumele.FeiraAPI.DTOs;

import java.time.LocalDateTime;

public record EventoResponseDTO(
    Long id,
    String titulo,
    LocalDateTime data,
    Long expositorId,
    String expositorNome
) {}

