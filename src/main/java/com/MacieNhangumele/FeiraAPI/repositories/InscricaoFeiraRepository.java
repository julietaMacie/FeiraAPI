package com.MacieNhangumele.FeiraAPI.repositories;

import com.MacieNhangumele.FeiraAPI.models.InscricaoFeira;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscricaoFeiraRepository extends JpaRepository<InscricaoFeira, Long> {
    List<InscricaoFeira> findByFeiraId(Long feiraId);
    List<InscricaoFeira> findByVisitanteId(Long visitanteId);
    boolean existsByFeiraIdAndVisitanteId(Long feiraId, Long visitanteId);
}