package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.InscricaoEventoResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewInscricaoEventoDTO;
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
    private final InscricaoEventoRepository inscricaoEventoRepository;
    private final EventoRepository eventoRepository;
    private final VisitanteRepository visitanteRepository;

    public InscricaoEventoService(InscricaoEventoRepository inscricaoEventoRepository,
                                EventoRepository eventoRepository,
                                VisitanteRepository visitanteRepository) {
        this.inscricaoEventoRepository = inscricaoEventoRepository;
        this.eventoRepository = eventoRepository;
        this.visitanteRepository = visitanteRepository;
    }

    public List<InscricaoEventoResponseDTO> getAll() {
        return inscricaoEventoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InscricaoEventoResponseDTO getById(Long id) {
        InscricaoEvento inscricao = inscricaoEventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscricao not found"));
        return convertToDTO(inscricao);
    }

    @Transactional
    public InscricaoEventoResponseDTO createInscricao(NewInscricaoEventoDTO dto) {
        if (inscricaoEventoRepository.existsByEventoIdAndVisitanteId(dto.eventoId(), dto.visitanteId())) {
            throw new RuntimeException("Visitante já inscrito neste evento");
        }
        
        Evento evento = eventoRepository.findById(dto.eventoId())
                .orElseThrow(() -> new RuntimeException("Evento not found"));
        
        Visitante visitante = visitanteRepository.findById(dto.visitanteId())
                .orElseThrow(() -> new RuntimeException("Visitante not found"));
        
        InscricaoEvento inscricao = new InscricaoEvento();
        inscricao.setEvento(evento);
        inscricao.setVisitante(visitante);
        
        InscricaoEvento savedInscricao = inscricaoEventoRepository.save(inscricao);
        return convertToDTO(savedInscricao);
    }

    @Transactional
    public void deleteInscricao(Long id) {
        inscricaoEventoRepository.deleteById(id);
    }

    public List<InscricaoEventoResponseDTO> getByEventoId(Long eventoId) {
        return inscricaoEventoRepository.findByEventoId(eventoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<InscricaoEventoResponseDTO> getByVisitanteId(Long visitanteId) {
        return inscricaoEventoRepository.findByVisitanteId(visitanteId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private InscricaoEventoResponseDTO convertToDTO(InscricaoEvento inscricao) {
        return new InscricaoEventoResponseDTO(
                inscricao.getId(),
                inscricao.getEvento().getId(),
                inscricao.getEvento().getTitulo(),
                inscricao.getVisitante().getId(),
                inscricao.getVisitante().getNome()
        );
    }
}