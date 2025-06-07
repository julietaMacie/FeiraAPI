package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.EventoDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.EventoDTO.Response;
import com.MacieNhangumele.FeiraAPI.services.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO.Response>> getAll() {
        return ResponseEntity.ok(eventoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO.Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(
            @RequestBody @Valid EventoDTO.Create dto) {
        return ResponseEntity.ok(eventoService.createEvento(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(
            @PathVariable Long id,
            @RequestBody @Valid EventoDTO.Update dto) {
        return ResponseEntity.ok(eventoService.updateEvento(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}