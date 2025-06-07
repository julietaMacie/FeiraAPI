package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorDTO;
import com.MacieNhangumele.FeiraAPI.models.Expositor;
import com.MacieNhangumele.FeiraAPI.models.Role;
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

    @Transactional
    public ExpositorDTO.Response createExpositor(ExpositorDTO.Create dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou não é EXPOSITOR"));
        
        if (user.getRole() != Role.EXPOSITOR) {
            throw new RuntimeException("Tipo de usuário não é EXPOSITOR");
        }

        Expositor expositor = Expositor.builder()
                .user(user)
                .nome(dto.nome())
                .tipo(dto.tipo())
                .linkStandOnline(dto.linkStandOnline())
                .numeroStandFisico(dto.numeroStandFisico())
                .build();
        
        Expositor saved = expositorRepository.save(expositor);
        return toDto(saved);
    }

    public List<ExpositorDTO.Response> getAll() {
        return expositorRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ExpositorDTO.Response getById(Long id) {
        return expositorRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
    }

    @Transactional
    public void deleteExpositor(Long id) {
        if (!expositorRepository.existsById(id)) {
            throw new RuntimeException("Expositor não encontrado");
        }
        expositorRepository.deleteById(id);
    }

    @Transactional
    public ExpositorDTO.Response updateExpositor(Long id, ExpositorDTO.Update dto) {
        Expositor expositor = expositorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));

        if (dto.nome() != null) {
            expositor.setNome(dto.nome());
        }
        
        if (dto.tipo() != null) {
            expositor.setTipo(dto.tipo());
        }
        
        expositor.setLinkStandOnline(dto.linkStandOnline());
        expositor.setNumeroStandFisico(dto.numeroStandFisico());

        Expositor updated = expositorRepository.save(expositor);
        return toDto(updated);
    }

    private ExpositorDTO.Response toDto(Expositor expositor) {
        return new ExpositorDTO.Response(
                expositor.getId(),
                expositor.getUser().getId(),
                expositor.getUser().getEmail(),
                expositor.getNome(),
                expositor.getTipo(),
                expositor.getLinkStandOnline(),
                expositor.getNumeroStandFisico()
        );
    }
}