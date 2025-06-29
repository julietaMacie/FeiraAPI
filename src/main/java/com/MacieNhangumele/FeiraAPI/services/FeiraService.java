package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.FeiraDTO;
import com.MacieNhangumele.FeiraAPI.models.Feira;
import com.MacieNhangumele.FeiraAPI.models.Estande;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.FeiraRepository;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import com.MacieNhangumele.FeiraAPI.repositories.EstandeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeiraService {
    private final FeiraRepository feiraRepository;
    private final UserRepository userRepository;
    private final EstandeRepository estandeRepository;

    public FeiraService(FeiraRepository feiraRepository, 
                       UserRepository userRepository, EstandeRepository estandeRepository) {
        this.feiraRepository = feiraRepository;
        this.userRepository = userRepository;
        this.estandeRepository = estandeRepository;
    }

    @Transactional
    public FeiraDTO.FeiraResponse createFeira(FeiraDTO.CreateFeira dto) {
        User expositor = userRepository.findById(dto.expositorId())
                .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));

        Estande estande = estandeRepository.findById(dto.estandeId())
                .orElseThrow(() -> new RuntimeException("Estande não encontrada"));

        Feira feira = Feira.builder()
                .titulo(dto.titulo())
                .data(dto.data())
                .expositor(expositor)
                .estande(estande)
                .build();

        Feira saved = feiraRepository.save(feira);
        return toDto(saved);
    }

    public List<FeiraDTO.FeiraResponse> getAll() {
        return feiraRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public FeiraDTO.FeiraResponse getById(Long id) {
        return feiraRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("feira não encontrado"));
    }

    @Transactional
    public void deleteFeira(Long id) {
        if (!feiraRepository.existsById(id)) {
            throw new RuntimeException("feira não encontrado");
        }
        feiraRepository.deleteById(id);
    }

    @Transactional
    public FeiraDTO.FeiraResponse updatefeira(Long id, FeiraDTO.UpdateFeira dto) {
        Feira feira = feiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("feira não encontrado"));

        if (dto.titulo() != null) {
            feira.setTitulo(dto.titulo());
        }
        
        if (dto.data() != null) {
            feira.setData(dto.data());
        }
        
        if (dto.expositorId() != null) {
            User expositor = userRepository.findById(dto.expositorId())
                    .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
            feira.setExpositor(expositor);
        }

        Feira updated = feiraRepository.save(feira);
        return toDto(updated);
    }

    private FeiraDTO.FeiraResponse toDto(Feira feira) {
        return new FeiraDTO.FeiraResponse(
                feira.getId(),
                feira.getTitulo(),
                feira.getData(),
                feira.getEstande().getId(),
                feira.getExpositor().getId(),
                feira.getExpositor().getNome()
        );
    }
}