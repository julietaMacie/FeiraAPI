package com.MacieNhangumele.FeiraAPI.DTOs;

import com.MacieNhangumele.FeiraAPI.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthDTOs {
    public record LoginRequest(
        @NotBlank String email,
        @NotBlank String password
    ) {}
    
    public record LoginResponse(String token) {}
    
    public record RegisterRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String nome,
        @NotNull Role role,
        
        String tipoAcesso,
        
        String tipo,
        String linkStandOnline,
        String numeroStandFisico
    ) {
        public RegisterRequest {
            if (role == Role.VISITANTE && tipoAcesso == null) {
                throw new IllegalArgumentException("tipoAcesso é obrigatório para visitantes");
            }
            
            if (role == Role.EXPOSITOR) {
                if (tipo == null) {
                    throw new IllegalArgumentException("tipo é obrigatório para expositores");
                }
                if (linkStandOnline == null) {
                    throw new IllegalArgumentException("linkStandOnline é obrigatório para expositores");
                }
                if (numeroStandFisico == null) {
                    throw new IllegalArgumentException("numeroStandFisico é obrigatório para expositores");
                }
            }
        }
    }
}