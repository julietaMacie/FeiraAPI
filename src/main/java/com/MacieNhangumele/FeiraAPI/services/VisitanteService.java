package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.VisitanteDTO;
import com.MacieNhangumele.FeiraAPI.models.Role;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitanteService {
    private final UserRepository userRepository;

    public VisitanteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<VisitanteDTO.VisitanteResponse> getAll() {
        return userRepository.findByRole(Role.VISITANTE).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public VisitanteDTO.VisitanteResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
        
        if (user.getRole() != Role.VISITANTE) {
            throw new RuntimeException("Usuário não é um visitante");
        }
        
        return toDto(user);
    }

    public void deleteVisitante(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
        
        if (user.getRole() != Role.VISITANTE) {
            throw new RuntimeException("Usuário não é um visitante");
        }
        
        userRepository.delete(user);
    }

    private VisitanteDTO.VisitanteResponse toDto(User user) {
        return new VisitanteDTO.VisitanteResponse(
                user.getId(),
                user.getEmail(),
                user.getNome(), // Você precisará adicionar este campo na classe User
                user.getTipoAcesso() // Você precisará adicionar este campo na classe User
        );
    }
}