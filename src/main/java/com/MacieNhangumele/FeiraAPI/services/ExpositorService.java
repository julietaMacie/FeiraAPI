package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewExpositorDTO;
import com.MacieNhangumele.FeiraAPI.models.Expositor;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.ExpositorRepository;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpositorService {
    private final ExpositorRepository expositorRepository;
    private final UserRepository userRepository;

    public ExpositorService(ExpositorRepository expositorRepository,
                          UserRepository userRepository) {
        this.expositorRepository = expositorRepository;
        this.userRepository = userRepository;
    }

    public List<ExpositorResponseDTO> getAll() {
        return expositorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExpositorResponseDTO getById(Long id) {
        Expositor expositor = expositorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expositor not found"));
        return convertToDTO(expositor);
    }

    @Transactional
    public ExpositorResponseDTO createExpositor(NewExpositorDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Expositor expositor = new Expositor();
        expositor.setUser(user);
        expositor.setNome(dto.nome());
        expositor.setTipo(dto.tipo());
        expositor.setLinkStandOnline(dto.linkStandOnline());
        expositor.setNumeroStandFisico(dto.numeroStandFisico());
        
        Expositor savedExpositor = expositorRepository.save(expositor);
        return convertToDTO(savedExpositor);
    }

    @Transactional
    public ExpositorResponseDTO updateExpositor(Long id, NewExpositorDTO dto) {
        Expositor expositor = expositorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expositor not found"));
        
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        expositor.setUser(user);
        expositor.setNome(dto.nome());
        expositor.setTipo(dto.tipo());
        expositor.setLinkStandOnline(dto.linkStandOnline());
        expositor.setNumeroStandFisico(dto.numeroStandFisico());
        
        Expositor updatedExpositor = expositorRepository.save(expositor);
        return convertToDTO(updatedExpositor);
    }

    @Transactional
    public void deleteExpositor(Long id) {
        expositorRepository.deleteById(id);
    }

    private ExpositorResponseDTO convertToDTO(Expositor expositor) {
        return ExpositorResponseDTO.builder()
                .id(expositor.getId())
                .userId(expositor.getUser().getId())
                .userEmail(expositor.getUser().getEmail())
                .nome(expositor.getNome())
                .tipo(expositor.getTipo())
                .linkStandOnline(expositor.getLinkStandOnline())
                .numeroStandFisico(expositor.getNumeroStandFisico())
                .build();
    }
}