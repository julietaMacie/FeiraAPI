package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.InscricaoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscricaoEventoRepository extends JpaRepository<InscricaoEvento, Long> {
    List<InscricaoEvento> findByEventoId(Long eventoId);
    List<InscricaoEvento> findByVisitanteId(Long visitanteId);
    boolean existsByEventoIdAndVisitanteId(Long eventoId, Long visitanteId);
}