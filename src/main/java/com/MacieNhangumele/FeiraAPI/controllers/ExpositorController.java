package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorResponseDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.NewExpositorDTO;
import com.MacieNhangumele.FeiraAPI.services.ExpositorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expositores")
public class ExpositorController {
    
    private final ExpositorService expositorService;

    public ExpositorController(ExpositorService expositorService) {
        this.expositorService = expositorService;
    }

    @GetMapping
    public ResponseEntity<List<ExpositorResponseDTO>> getAllExpositores() {
        List<ExpositorResponseDTO> expositores = expositorService.getAll();
        return ResponseEntity.ok(expositores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpositorResponseDTO> getExpositorById(@PathVariable Long id) {
        ExpositorResponseDTO expositor = expositorService.getById(id);
        return ResponseEntity.ok(expositor);
    }

    @PostMapping
    public ResponseEntity<ExpositorResponseDTO> createExpositor(@RequestBody @Valid NewExpositorDTO dto) {
        ExpositorResponseDTO newExpositor = expositorService.createExpositor(dto);
        return ResponseEntity.ok(newExpositor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpositorResponseDTO> updateExpositor(
            @PathVariable Long id,
            @RequestBody @Valid NewExpositorDTO dto) {
        ExpositorResponseDTO updatedExpositor = expositorService.updateExpositor(id, dto);
        return ResponseEntity.ok(updatedExpositor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpositor(@PathVariable Long id) {
        expositorService.deleteExpositor(id);
        return ResponseEntity.noContent().build();
    }
}