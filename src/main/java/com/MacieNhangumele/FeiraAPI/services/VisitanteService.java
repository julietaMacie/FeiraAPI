package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.VisitanteResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewVisitanteDTO;
import com.MacieNhangumele.FeiraAPI.models.Visitante;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.VisitanteRepository;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitanteService {
    private final VisitanteRepository visitanteRepository;
    private final UserRepository userRepository;

    public VisitanteService(VisitanteRepository visitanteRepository,
                          UserRepository userRepository) {
        this.visitanteRepository = visitanteRepository;
        this.userRepository = userRepository;
    }

    public List<VisitanteResponseDTO> getAll() {
        return visitanteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VisitanteResponseDTO getById(Long id) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante not found"));
        return convertToDTO(visitante);
    }

    @Transactional
    public VisitanteResponseDTO createVisitante(NewVisitanteDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Visitante visitante = new Visitante();
        visitante.setUser(user);
        visitante.setNome(dto.nome());
        visitante.setTipoAcesso(dto.tipoAcesso());
        
        Visitante savedVisitante = visitanteRepository.save(visitante);
        return convertToDTO(savedVisitante);
    }

    @Transactional
    public VisitanteResponseDTO updateVisitante(Long id, NewVisitanteDTO dto) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante not found"));
        
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        visitante.setUser(user);
        visitante.setNome(dto.nome());
        visitante.setTipoAcesso(dto.tipoAcesso());
        
        Visitante updatedVisitante = visitanteRepository.save(visitante);
        return convertToDTO(updatedVisitante);
    }

    @Transactional
    public void deleteVisitante(Long id) {
        visitanteRepository.deleteById(id);
    }

    private VisitanteResponseDTO convertToDTO(Visitante visitante) {
        return VisitanteResponseDTO.builder()
                .id(visitante.getId())
                .userId(visitante.getUser().getId())
                .userEmail(visitante.getUser().getEmail())
                .nome(visitante.getNome())
                .tipoAcesso(visitante.getTipoAcesso())
                .build();
    }
}