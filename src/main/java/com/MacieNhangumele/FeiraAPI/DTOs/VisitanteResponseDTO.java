package com.MacieNhangumele.FeiraAPI.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisitanteResponseDTO {
    private Long id;
    private Long userId;
    private String userEmail;
    private String nome;
    private String tipoAcesso;
}

