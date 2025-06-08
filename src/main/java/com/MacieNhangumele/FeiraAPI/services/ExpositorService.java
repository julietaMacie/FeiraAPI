package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorDTO;
import com.MacieNhangumele.FeiraAPI.models.Role;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpositorService {
    private final UserRepository userRepository;

    public ExpositorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ExpositorDTO.ExpositorResponse> getAll() {
        return userRepository.findByRole(Role.EXPOSITOR).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ExpositorDTO.ExpositorResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
        
        if (user.getRole() != Role.EXPOSITOR) {
            throw new RuntimeException("Utilizador não é um expositor");
        }
        
        return toDto(user);
    }

    public void deleteExpositor(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
        
        if (user.getRole() != Role.EXPOSITOR) {
            throw new RuntimeException("Utilizador não é um expositor");
        }
        
        userRepository.delete(user);
    }

    private ExpositorDTO.ExpositorResponse toDto(User user) {
        return new ExpositorDTO.ExpositorResponse(
                user.getId(),
                user.getEmail(),
                user.getNome(), 
                user.getTipo(), 
                user.getLinkStandOnline(), 
                user.getNumeroStandFisico() 
        );
    }
}