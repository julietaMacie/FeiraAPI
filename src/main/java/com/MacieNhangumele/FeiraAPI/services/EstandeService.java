package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO.Response;
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

    @Transactional
    public Response createEstande(EstandeDTO.Create dto) {
        Estande estande = Estande.builder()
                .localizacao(dto.localizacao())
                .status(dto.status())
                .expositor(dto.expositorId() != null ? 
                    expositorRepository.findById(dto.expositorId()).orElse(null) : null)
                .build();

        Estande saved = estandeRepository.save(estande);
        return toDto(saved);
    }

    private EstandeDTO.Response toDto(Estande estande) {
    return new EstandeDTO.Response(
        estande.getId(),
        estande.getLocalizacao(),
        estande.getStatus(),
        estande.getExpositor() != null ? estande.getExpositor().getId() : null,
        estande.getExpositor() != null ? estande.getExpositor().getNome() : null
    );
}

    public List<Response> getAll() {
        return estandeRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Response getById(Long id) {
        return estandeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Estande não encontrado"));
    }

    @Transactional
    public void deleteEstande(Long id) {
        if (!estandeRepository.existsById(id)) {
            throw new RuntimeException("Estande não encontrado");
        }
        estandeRepository.deleteById(id);
    }

    @Transactional
    public Response updateEstande(Long id, EstandeDTO.Update dto) {
        Estande estande = estandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estande não encontrado"));

        if (dto.localizacao() != null) {
            estande.setLocalizacao(dto.localizacao());
        }
        
        if (dto.status() != null) {
            estande.setStatus(dto.status());
        }
        
        if (dto.expositorId() != null) {
            Expositor expositor = expositorRepository.findById(dto.expositorId())
                    .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
            estande.setExpositor(expositor);
        } else {
            estande.setExpositor(null);
        }

        Estande updated = estandeRepository.save(estande);
        return toDto(updated);
    }
}