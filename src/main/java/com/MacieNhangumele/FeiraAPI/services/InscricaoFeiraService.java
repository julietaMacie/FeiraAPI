package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.InscricaoDTO;
import com.MacieNhangumele.FeiraAPI.models.Feira;
import com.MacieNhangumele.FeiraAPI.models.InscricaoFeira;
import com.MacieNhangumele.FeiraAPI.models.User;
import com.MacieNhangumele.FeiraAPI.repositories.FeiraRepository;
import com.MacieNhangumele.FeiraAPI.repositories.InscricaoFeiraRepository;
import com.MacieNhangumele.FeiraAPI.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoFeiraService {
    private final InscricaoFeiraRepository repository;
    private final FeiraRepository feiraRepository;
    private final UserRepository userRepository;

    public InscricaoFeiraService(InscricaoFeiraRepository repository,
                                FeiraRepository feiraRepository,
                                UserRepository userRepository) {
        this.repository = repository;
        this.feiraRepository = feiraRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public com.MacieNhangumele.FeiraAPI.DTOs.InscricaoDTO.Response createInscricao(InscricaoDTO.Create dto) {
        if (repository.existsByFeiraIdAndVisitanteId(dto.feiraId(), dto.visitanteId())) {
            throw new RuntimeException("Visitante já inscrito neste feira");
        }
        
        Feira feira = feiraRepository.findById(dto.feiraId())
                .orElseThrow(() -> new RuntimeException("feira não encontrado"));
        
        User visitante = userRepository.findById(dto.visitanteId())
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
        
        InscricaoFeira inscricao = InscricaoFeira.builder()
                .feira(feira)
                .visitante(visitante)
                .build();
        
        InscricaoFeira saved = repository.save(inscricao);
        return toDto(saved);
    }

    public List<InscricaoDTO.Response> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public InscricaoDTO.Response getById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada"));
    }

    @Transactional
    public void deleteInscricao(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Inscrição não encontrada");
        }
        repository.deleteById(id);
    }

    public List<InscricaoDTO.Response> getByFeiraId(Long feiraId) {
        return repository.findByFeiraId(feiraId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<InscricaoDTO.Response> getByVisitanteId(Long visitanteId) {
        return repository.findByVisitanteId(visitanteId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private InscricaoDTO.Response toDto(InscricaoFeira inscricao) {
        return new InscricaoDTO.Response(
                inscricao.getId(),
                inscricao.getFeira().getId(),
                inscricao.getFeira().getTitulo(),
                inscricao.getVisitante().getId(),
                inscricao.getVisitante().getNome(),
                inscricao.getDataInscricao()
        );
    }
}