package com.MacieNhangumele.FeiraAPI.DTOs;

import com.MacieNhangumele.FeiraAPI.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
    @NotBlank String email,
    @NotBlank String password,
    @NotNull Role role,
    
    String nome,
    String tipo,
    String linkStandOnline,
    String numeroStandFisico,
    
    String visitanteNome,
    String tipoAcesso
) {}