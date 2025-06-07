package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.EstandeResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewEstandeDTO;
import com.MacieNhangumele.FeiraAPI.models.Estande;
import com.MacieNhangumele.FeiraAPI.models.Expositor;
import com.MacieNhangumele.FeiraAPI.repositories.EstandeRepository;
import com.MacieNhangumele.FeiraAPI.repositories.ExpositorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstandeService {
    private final EstandeRepository estandeRepository;
    private final ExpositorRepository expositorRepository;

    public EstandeService(EstandeRepository estandeRepository, 
                        ExpositorRepository expositorRepository) {
        this.estandeRepository = estandeRepository;
        this.expositorRepository = expositorRepository;
    }

    public List<EstandeResponseDTO> getAll() {
        return estandeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EstandeResponseDTO getById(Long id) {
        Estande estande = estandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estande not found"));
        return convertToDTO(estande);
    }

    @Transactional
    public EstandeResponseDTO createEstande(NewEstandeDTO dto) {
        Estande estande = new Estande();
        estande.setLocalizacao(dto.localizacao());
        estande.setStatus(dto.status());
        
        if (dto.expositorId() != null) {
            Expositor expositor = expositorRepository.findById(dto.expositorId())
                    .orElseThrow(() -> new RuntimeException("Expositor not found"));
            estande.setExpositor(expositor);
        }
        
        Estande savedEstande = estandeRepository.save(estande);
        return convertToDTO(savedEstande);
    }

    @Transactional
    public EstandeResponseDTO updateEstande(Long id, NewEstandeDTO dto) {
        Estande estande = estandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estande not found"));
        
        estande.setLocalizacao(dto.localizacao());
        estande.setStatus(dto.status());
        
        if (dto.expositorId() != null) {
            Expositor expositor = expositorRepository.findById(dto.expositorId())
                    .orElseThrow(() -> new RuntimeException("Expositor not found"));
            estande.setExpositor(expositor);
        } else {
            estande.setExpositor(null);
        }
        
        Estande updatedEstande = estandeRepository.save(estande);
        return convertToDTO(updatedEstande);
    }

    @Transactional
    public void deleteEstande(Long id) {
        estandeRepository.deleteById(id);
    }

    private EstandeResponseDTO convertToDTO(Estande estande) {
        return EstandeResponseDTO.builder()
                .id(estande.getId())
                .localizacao(estande.getLocalizacao())
                .status(estande.getStatus())
                .expositorId(estande.getExpositor() != null ? estande.getExpositor().getId() : null)
                .expositorNome(estande.getExpositor() != null ? estande.getExpositor().getNome() : null)
                .build();
    }
}