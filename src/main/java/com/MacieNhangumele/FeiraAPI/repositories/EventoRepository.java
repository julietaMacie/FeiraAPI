package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByTituloContainingIgnoreCase(String titulo);
    List<Evento> findByDataBetween(LocalDateTime start, LocalDateTime end);
    List<Evento> findByExpositorId(Long expositorId);
}