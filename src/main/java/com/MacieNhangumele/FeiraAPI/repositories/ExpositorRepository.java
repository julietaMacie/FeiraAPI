package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.Expositor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpositorRepository extends JpaRepository<Expositor, Long> {
    List<Expositor> findByNomeContainingIgnoreCase(String nome);
    List<Expositor> findByTipo(String tipo);
    Expositor findByUserId(Long userId);
}