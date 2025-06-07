package com.MacieNhangumele.FeiraAPI.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Evento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String titulo;  
    
    @Column(nullable = false)
    private LocalDateTime data;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expositor_id")  
    private Expositor expositor;  
}