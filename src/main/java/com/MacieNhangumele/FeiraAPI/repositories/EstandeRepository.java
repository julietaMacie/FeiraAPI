package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.Estande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstandeRepository extends JpaRepository<Estande, Long> {
    List<Estande> findByLocalizacaoContainingIgnoreCase(String localizacao);
    List<Estande> findByStatus(String status);
    List<Estande> findByExpositorId(Long expositorId);
}