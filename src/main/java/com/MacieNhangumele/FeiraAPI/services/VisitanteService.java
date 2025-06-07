package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.models.Visitante;
import com.MacieNhangumele.FeiraAPI.DTOs.VisitanteDTO;
import com.MacieNhangumele.FeiraAPI.models.Role;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.VisitanteRepository;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class VisitanteService {
    private final VisitanteRepository visitanteRepository;
    private final UserRepository userRepository;

    public VisitanteService(VisitanteRepository visitanteRepository,
                          UserRepository userRepository) {
        this.visitanteRepository = visitanteRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public VisitanteDTO.VisitanteResponse createVisitante(VisitanteDTO.CreateVisitante dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou não é VISITANTE"));
        
        if (user.getRole() != Role.VISITANTE) {
            throw new RuntimeException("Tipo de usuário não é VISITANTE");
        }

        Visitante visitante = Visitante.builder()
                .user(user)
                .nome(dto.nome())
                .tipoAcesso(dto.tipoAcesso())
                .build();
        
        Visitante saved = visitanteRepository.save(visitante);
        return toDto(saved);
    }

    public List<VisitanteDTO.VisitanteResponse> getAll() {
        return visitanteRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public VisitanteDTO.VisitanteResponse getById(Long id) {
        return visitanteRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
    }

    @Transactional
    public void deleteVisitante(Long id) {
        if (!visitanteRepository.existsById(id)) {
            throw new RuntimeException("Visitante não encontrado");
        }
        visitanteRepository.deleteById(id);
    }

    @Transactional
    public VisitanteDTO.VisitanteResponse updateVisitante(Long id, VisitanteDTO.UpdateVisitante dto) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));

        if (dto.nome() != null) {
            visitante.setNome(dto.nome());
        }
        
        if (dto.tipoAcesso() != null) {
            visitante.setTipoAcesso(dto.tipoAcesso());
        }

        Visitante updated = visitanteRepository.save(visitante);
        return toDto(updated);
    }

    private VisitanteDTO.VisitanteResponse toDto(Visitante visitante) {
        return new VisitanteDTO.VisitanteResponse(
                visitante.getId(),
                visitante.getUser().getId(),
                visitante.getUser().getEmail(),
                visitante.getNome(),
                visitante.getTipoAcesso()
        );
    }
}