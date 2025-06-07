package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.VisitanteResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewVisitanteDTO;
import com.MacieNhangumele.FeiraAPI.services.VisitanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/visitantes")
public class VisitanteController {
    
    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @GetMapping
    public ResponseEntity<List<VisitanteResponseDTO>> getAllVisitantes() {
        List<VisitanteResponseDTO> visitantes = visitanteService.getAll();
        return ResponseEntity.ok(visitantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteResponseDTO> getVisitanteById(@PathVariable Long id) {
        VisitanteResponseDTO visitante = visitanteService.getById(id);
        return ResponseEntity.ok(visitante);
    }

    @PostMapping
    public ResponseEntity<VisitanteResponseDTO> createVisitante(@RequestBody @Valid NewVisitanteDTO dto) {
        VisitanteResponseDTO newVisitante = visitanteService.createVisitante(dto);
        return ResponseEntity.ok(newVisitante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitanteResponseDTO> updateVisitante(
            @PathVariable Long id,
            @RequestBody @Valid NewVisitanteDTO dto) {
        VisitanteResponseDTO updatedVisitante = visitanteService.updateVisitante(id, dto);
        return ResponseEntity.ok(updatedVisitante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitante(@PathVariable Long id) {
        visitanteService.deleteVisitante(id);
        return ResponseEntity.noContent().build();
    }
}