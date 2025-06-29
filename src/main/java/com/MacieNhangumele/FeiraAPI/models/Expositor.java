package com.MacieNhangumele.FeiraAPI.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Expositor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, length = 50)  
    private String tipo;  
    
    @Column(length = 255)
    private String linkStandOnline;  
    
    @Column(length = 20)
    private String numeroStandFisico; 
}