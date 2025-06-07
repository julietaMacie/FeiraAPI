package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.EstandeResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewEstandeDTO;
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
    public ResponseEntity<List<EstandeResponseDTO>> getAllEstandes() {
        List<EstandeResponseDTO> estandes = estandeService.getAll();
        return ResponseEntity.ok(estandes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstandeResponseDTO> getEstandeById(@PathVariable Long id) {
        EstandeResponseDTO estande = estandeService.getById(id);
        return ResponseEntity.ok(estande);
    }

    @PostMapping
    public ResponseEntity<EstandeResponseDTO> createEstande(@RequestBody @Valid NewEstandeDTO dto) {
        EstandeResponseDTO newEstande = estandeService.createEstande(dto);
        return ResponseEntity.ok(newEstande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstandeResponseDTO> updateEstande(
            @PathVariable Long id,
            @RequestBody @Valid NewEstandeDTO dto) {
        EstandeResponseDTO updatedEstande = estandeService.updateEstande(id, dto);
        return ResponseEntity.ok(updatedEstande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstande(@PathVariable Long id) {
        estandeService.deleteEstande(id);
        return ResponseEntity.noContent().build();
    }
}