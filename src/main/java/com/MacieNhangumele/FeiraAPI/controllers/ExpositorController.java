package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorDTO.Response;
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
    public ResponseEntity<List<Response>> getAllExpositores() {
        return ResponseEntity.ok(expositorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getExpositorById(@PathVariable Long id) {
        return ResponseEntity.ok(expositorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> createExpositor(
            @RequestBody @Valid ExpositorDTO.Create dto) {
        
        return ResponseEntity.ok(expositorService.createExpositor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateExpositor(
            @PathVariable Long id,
            @RequestBody @Valid ExpositorDTO.Update dto) {
        
        return ResponseEntity.ok(expositorService.updateExpositor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpositor(@PathVariable Long id) {
        expositorService.deleteExpositor(id);
        return ResponseEntity.noContent().build();
    }
}