package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.ExpositorDTO;
import com.MacieNhangumele.FeiraAPI.services.ExpositorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/expositores")
public class ExpositorController {
    
    private final ExpositorService expositorService;

    public ExpositorController(ExpositorService expositorService) {
        this.expositorService = expositorService;
    }

    @GetMapping
    public ResponseEntity<List<ExpositorDTO.ExpositorResponse>> getAllExpositores() {
        return ResponseEntity.ok(expositorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpositorDTO.ExpositorResponse> getExpositorById(@PathVariable Long id) {
        return ResponseEntity.ok(expositorService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpositor(@PathVariable Long id) {
        expositorService.deleteExpositor(id);
        return ResponseEntity.noContent().build();
    }
}