package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO.Response;
import com.MacieNhangumele.FeiraAPI.services.EstandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estandes")
public class EstandeController {
    
    private final EstandeService estandeService;

    public EstandeController(EstandeService estandeService) {
        this.estandeService = estandeService;
    }

    @GetMapping
    public ResponseEntity<List<Response>> getAll() {
        return ResponseEntity.ok(estandeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(estandeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> create(
            @RequestBody @Valid EstandeDTO.Create dto) {
        return ResponseEntity.ok(estandeService.createEstande(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(
            @PathVariable Long id,
            @RequestBody @Valid EstandeDTO.Update dto) {
        return ResponseEntity.ok(estandeService.updateEstande(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estandeService.deleteEstande(id);
        return ResponseEntity.noContent().build();
    }
}