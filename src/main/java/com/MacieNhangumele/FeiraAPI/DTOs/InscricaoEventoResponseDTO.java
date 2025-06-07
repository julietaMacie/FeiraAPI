package com.MacieNhangumele.FeiraAPI.DTOs;

public record InscricaoEventoResponseDTO(
    Long id,
    Long eventoId,
    String eventoTitulo,
    Long visitanteId,
    String visitanteNome
) {}