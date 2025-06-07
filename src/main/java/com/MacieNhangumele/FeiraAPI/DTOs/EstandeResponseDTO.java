package com.MacieNhangumele.FeiraAPI.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstandeResponseDTO {
    private Long id;
    private String localizacao;
    private String status;
    private Long expositorId;
    private String expositorNome;
}
