package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.VisitanteDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.VisitanteDTO.Response;
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
    public ResponseEntity<List<VisitanteDTO.Response>> getAll() {
        return ResponseEntity.ok(visitanteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteDTO.Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(visitanteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(
            @RequestBody @Valid VisitanteDTO.Create dto) {
        return ResponseEntity.ok(visitanteService.createVisitante(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(
            @PathVariable Long id,
            @RequestBody @Valid VisitanteDTO.Update dto) {
        return ResponseEntity.ok(visitanteService.updateVisitante(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        visitanteService.deleteVisitante(id);
        return ResponseEntity.noContent().build();
    }
}