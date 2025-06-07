package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.InscricaoDTO;
import com.MacieNhangumele.FeiraAPI.services.InscricaoEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoEventoController {
    
    private final InscricaoEventoService service;

    public InscricaoEventoController(InscricaoEventoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InscricaoDTO.Response>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDTO.Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<InscricaoDTO.Response> create(
            @RequestBody @Valid InscricaoDTO.Create dto) {
        return ResponseEntity.ok(service.createInscricao(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteInscricao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<InscricaoDTO.Response>> getByEvento(
            @PathVariable Long eventoId) {
        return ResponseEntity.ok(service.getByEventoId(eventoId));
    }

    @GetMapping("/visitante/{visitanteId}")
    public ResponseEntity<List<InscricaoDTO.Response>> getByVisitante(
            @PathVariable Long visitanteId) {
        return ResponseEntity.ok(service.getByVisitanteId(visitanteId));
    }
}