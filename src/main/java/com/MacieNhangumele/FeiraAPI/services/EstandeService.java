package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO.EstandeResponse;
import com.MacieNhangumele.FeiraAPI.models.Estande;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.EstandeRepository;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstandeService {
    private final EstandeRepository estandeRepository;
    private final UserRepository userRepository;

    public EstandeService(EstandeRepository estandeRepository, 
                        UserRepository userRepository) {
        this.estandeRepository = estandeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public EstandeResponse createEstande(EstandeDTO.CreateEstande dto) {
        Estande estande = Estande.builder()
                .localizacao(dto.localizacao())
                .status(dto.status())
                .expositor(dto.expositorId() != null ? 
                    userRepository.findById(dto.expositorId()).orElse(null) : null)
                .build();

        Estande saved = estandeRepository.save(estande);
        return toDto(saved);
    }

    private EstandeDTO.EstandeResponse toDto(Estande estande) {
    return new EstandeDTO.EstandeResponse(
        estande.getId(),
        estande.getLocalizacao(),
        estande.getStatus(),
        estande.getExpositor() != null ? estande.getExpositor().getId() : null,
        estande.getExpositor() != null ? estande.getExpositor().getNome() : null
    );
}

    public List<EstandeResponse> getAll() {
        return estandeRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EstandeResponse getById(Long id) {
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
    public EstandeResponse updateEstande(Long id, EstandeDTO.UpdateEstande dto) {
        Estande estande = estandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estande não encontrado"));

        if (dto.localizacao() != null) {
            estande.setLocalizacao(dto.localizacao());
        }
        
        if (dto.status() != null) {
            estande.setStatus(dto.status());
        }
        
        if (dto.expositorId() != null) {
            User expositor = userRepository.findById(dto.expositorId())
                    .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
            estande.setExpositor(expositor);
        } else {
            estande.setExpositor(null);
        }

        Estande updated = estandeRepository.save(estande);
        return toDto(updated);
    }
}