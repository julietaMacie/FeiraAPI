package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.FeiraDTO;
import com.MacieNhangumele.FeiraAPI.DTOs.FeiraDTO.FeiraResponse;
import com.MacieNhangumele.FeiraAPI.services.FeiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/feiras")
public class FeiraController {
    
    private final FeiraService feiraService;

    public FeiraController(FeiraService feiraService) {
        this.feiraService = feiraService;
    }

    @GetMapping
    public ResponseEntity<List<FeiraDTO.FeiraResponse>> getAll() {
        return ResponseEntity.ok(feiraService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeiraDTO.FeiraResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(feiraService.getById(id));
    }

    @PostMapping
    public ResponseEntity<FeiraResponse> create(
            @RequestBody @Valid FeiraDTO.CreateFeira dto) {
        return ResponseEntity.ok(feiraService.createFeira(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeiraResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid FeiraDTO.UpdateFeira dto) {
        return ResponseEntity.ok(feiraService.updatefeira(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feiraService.deleteFeira(id);
        return ResponseEntity.noContent().build();
    }
}