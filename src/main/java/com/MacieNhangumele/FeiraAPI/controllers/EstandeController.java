package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.EstandeDTO.EstandeResponse;
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
    public ResponseEntity<List<EstandeResponse>> getAll() {
        return ResponseEntity.ok(estandeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstandeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(estandeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EstandeResponse> create(
            @RequestBody @Valid EstandeDTO.CreateEstande dto) {
        return ResponseEntity.ok(estandeService.createEstande(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstandeResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid EstandeDTO.UpdateEstande dto) {
        return ResponseEntity.ok(estandeService.updateEstande(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estandeService.deleteEstande(id);
        return ResponseEntity.noContent().build();
    }
}