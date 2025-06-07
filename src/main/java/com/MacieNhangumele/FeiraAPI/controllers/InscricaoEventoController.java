package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.InscricaoEventoResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewInscricaoEventoDTO;
import com.MacieNhangumele.FeiraAPI.services.InscricaoEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoEventoController {
    
    private final InscricaoEventoService inscricaoEventoService;

    public InscricaoEventoController(InscricaoEventoService inscricaoEventoService) {
        this.inscricaoEventoService = inscricaoEventoService;
    }

    @GetMapping
    public ResponseEntity<List<InscricaoEventoResponseDTO>> getAllInscricoes() {
        List<InscricaoEventoResponseDTO> inscricoes = inscricaoEventoService.getAll();
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoEventoResponseDTO> getInscricaoById(@PathVariable Long id) {
        InscricaoEventoResponseDTO inscricao = inscricaoEventoService.getById(id);
        return ResponseEntity.ok(inscricao);
    }

    @PostMapping
    public ResponseEntity<InscricaoEventoResponseDTO> createInscricao(
            @RequestBody @Valid NewInscricaoEventoDTO dto) {
        InscricaoEventoResponseDTO newInscricao = inscricaoEventoService.createInscricao(dto);
        return ResponseEntity.ok(newInscricao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscricao(@PathVariable Long id) {
        inscricaoEventoService.deleteInscricao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoEventoResponseDTO>> getInscricoesByEvento(
            @PathVariable Long eventoId) {
        List<InscricaoEventoResponseDTO> inscricoes = 
                inscricaoEventoService.getByEventoId(eventoId);
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/visitante/{visitanteId}")
    public ResponseEntity<List<InscricaoEventoResponseDTO>> getInscricoesByVisitante(
            @PathVariable Long visitanteId) {
        List<InscricaoEventoResponseDTO> inscricoes = 
                inscricaoEventoService.getByVisitanteId(visitanteId);
        return ResponseEntity.ok(inscricoes);
    }
}