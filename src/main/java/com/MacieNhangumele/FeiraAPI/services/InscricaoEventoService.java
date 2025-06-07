package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.InscricaoDTO;
import com.MacieNhangumele.FeiraAPI.models.Evento;
import com.MacieNhangumele.FeiraAPI.models.InscricaoEvento;
import com.MacieNhangumele.FeiraAPI.models.Visitante;
import com.MacieNhangumele.FeiraAPI.repositories.EventoRepository;
import com.MacieNhangumele.FeiraAPI.repositories.InscricaoEventoRepository;
import com.MacieNhangumele.FeiraAPI.repositories.VisitanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoEventoService {
    private final InscricaoEventoRepository repository;
    private final EventoRepository eventoRepository;
    private final VisitanteRepository visitanteRepository;

    public InscricaoEventoService(InscricaoEventoRepository repository,
                                EventoRepository eventoRepository,
                                VisitanteRepository visitanteRepository) {
        this.repository = repository;
        this.eventoRepository = eventoRepository;
        this.visitanteRepository = visitanteRepository;
    }

    @Transactional
    public com.MacieNhangumele.FeiraAPI.DTOs.InscricaoDTO.Response createInscricao(InscricaoDTO.Create dto) {
        if (repository.existsByEventoIdAndVisitanteId(dto.eventoId(), dto.visitanteId())) {
            throw new RuntimeException("Visitante já inscrito neste evento");
        }
        
        Evento evento = eventoRepository.findById(dto.eventoId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        Visitante visitante = visitanteRepository.findById(dto.visitanteId())
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado"));
        
        InscricaoEvento inscricao = InscricaoEvento.builder()
                .evento(evento)
                .visitante(visitante)
                .build();
        
        InscricaoEvento saved = repository.save(inscricao);
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

    public List<InscricaoDTO.Response> getByEventoId(Long eventoId) {
        return repository.findByEventoId(eventoId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<InscricaoDTO.Response> getByVisitanteId(Long visitanteId) {
        return repository.findByVisitanteId(visitanteId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private InscricaoDTO.Response toDto(InscricaoEvento inscricao) {
        return new InscricaoDTO.Response(
                inscricao.getId(),
                inscricao.getEvento().getId(),
                inscricao.getEvento().getTitulo(),
                inscricao.getVisitante().getId(),
                inscricao.getVisitante().getNome(),
                inscricao.getDataInscricao()
        );
    }
}