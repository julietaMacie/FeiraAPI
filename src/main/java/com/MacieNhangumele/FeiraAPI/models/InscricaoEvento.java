package com.MacieNhangumele.FeiraAPI.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class InscricaoEvento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitante_id", nullable = false)
    private Visitante visitante;
    
    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime dataInscricao = LocalDateTime.now();
}