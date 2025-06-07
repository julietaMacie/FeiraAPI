package com.MacieNhangumele.FeiraAPI.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    private LocalDateTime data;
    
    @ManyToOne
    @JoinColumn(name = "expositor_id", nullable = false)
    private Expositor expositor;
}