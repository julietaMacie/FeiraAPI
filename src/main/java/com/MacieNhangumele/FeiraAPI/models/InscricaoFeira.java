package com.MacieNhangumele.FeiraAPI.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class InscricaoFeira {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feira_id", nullable = false)
    private Feira feira;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User visitante;
    
    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime dataInscricao = LocalDateTime.now();
}