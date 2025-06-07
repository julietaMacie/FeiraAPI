package com.MacieNhangumele.FeiraAPI.services;

import com.MacieNhangumele.FeiraAPI.DTOs.EventoDTO;
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

    @Transactional
    public EventoDTO.Response createEvento(EventoDTO.Create dto) {
        Expositor expositor = expositorRepository.findById(dto.expositorId())
                .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));

        Evento evento = Evento.builder()
                .titulo(dto.titulo())
                .data(dto.data())
                .expositor(expositor)
                .build();

        Evento saved = eventoRepository.save(evento);
        return toDto(saved);
    }

    public List<EventoDTO.Response> getAll() {
        return eventoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EventoDTO.Response getById(Long id) {
        return eventoRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    @Transactional
    public void deleteEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        eventoRepository.deleteById(id);
    }

    @Transactional
    public EventoDTO.Response updateEvento(Long id, EventoDTO.Update dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (dto.titulo() != null) {
            evento.setTitulo(dto.titulo());
        }
        
        if (dto.data() != null) {
            evento.setData(dto.data());
        }
        
        if (dto.expositorId() != null) {
            Expositor expositor = expositorRepository.findById(dto.expositorId())
                    .orElseThrow(() -> new RuntimeException("Expositor não encontrado"));
            evento.setExpositor(expositor);
        }

        Evento updated = eventoRepository.save(evento);
        return toDto(updated);
    }

    private EventoDTO.Response toDto(Evento evento) {
        return new EventoDTO.Response(
                evento.getId(),
                evento.getTitulo(),
                evento.getData(),
                evento.getExpositor().getId(),
                evento.getExpositor().getNome()
        );
    }
}