package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.Feira;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FeiraRepository extends JpaRepository<Feira, Long> {
    List<Feira> findByTituloContainingIgnoreCase(String titulo);
    List<Feira> findByDataBetween(LocalDateTime start, LocalDateTime end);
    List<Feira> findByExpositorId(Long expositorId);
}