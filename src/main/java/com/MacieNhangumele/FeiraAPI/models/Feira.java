package com.MacieNhangumele.FeiraAPI.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Feira {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String titulo;  
    
    @Column(nullable = false)
    private LocalDateTime data;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  
    private User expositor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estande_id")  
    private Estande estande; 
}