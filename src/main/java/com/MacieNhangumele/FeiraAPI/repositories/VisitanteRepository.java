package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    List<Visitante> findByNomeContainingIgnoreCase(String nome);
    List<Visitante> findByTipoAcesso(String tipoAcesso);
    Visitante findByUserId(Long userId);
}