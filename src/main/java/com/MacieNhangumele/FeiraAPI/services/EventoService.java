package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.EventoResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewEventoDTO;
import com.MacieNhangumele.FeiraAPI.models.Evento;
import com.MacieNhangumele.FeiraAPI.models.Expositor;
import com.MacieNhangumele.FeiraAPI.repositories.EventoRepository;
import com.MacieNhangumele.FeiraAPI.repositories.ExpositorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;
    private final ExpositorRepository expositorRepository;

    public EventoService(EventoRepository eventoRepository, 
                       ExpositorRepository expositorRepository) {
        this.eventoRepository = eventoRepository;
        this.expositorRepository = expositorRepository;
    }

    public List<EventoResponseDTO> getAll() {
        return eventoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventoResponseDTO getById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento not found"));
        return convertToDTO(evento);
    }

    @Transactional
    public EventoResponseDTO createEvento(NewEventoDTO dto) {
        Expositor expositor = expositorRepository.findById(dto.expositorId())
                .orElseThrow(() -> new RuntimeException("Expositor not found"));
        
        Evento evento = new Evento();
        evento.setTitulo(dto.titulo());
        evento.setData(dto.data());
        evento.setExpositor(expositor);
        
        Evento savedEvento = eventoRepository.save(evento);
        return convertToDTO(savedEvento);
    }

    @Transactional
    public EventoResponseDTO updateEvento(Long id, NewEventoDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento not found"));
        
        Expositor expositor = expositorRepository.findById(dto.expositorId())
                .orElseThrow(() -> new RuntimeException("Expositor not found"));
        
        evento.setTitulo(dto.titulo());
        evento.setData(dto.data());
        evento.setExpositor(expositor);
        
        Evento updatedEvento = eventoRepository.save(evento);
        return convertToDTO(updatedEvento);
    }

    @Transactional
    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    private EventoResponseDTO convertToDTO(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getTitulo(),
                evento.getData(),
                evento.getExpositor().getId(),
                evento.getExpositor().getNome()
        );
    }
}