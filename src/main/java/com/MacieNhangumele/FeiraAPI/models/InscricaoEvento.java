package com.MacieNhangumele.FeiraAPI.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscricaoEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;
    
    @ManyToOne
    @JoinColumn(name = "visitante_id", nullable = false)
    private Visitante visitante;
}