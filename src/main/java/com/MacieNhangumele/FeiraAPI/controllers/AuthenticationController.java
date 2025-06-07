package com.MacieNhangumele.FeiraAPI.controllers;

import com.MacieNhangumele.FeiraAPI.DTOs.*;
import com.MacieNhangumele.FeiraAPI.models.*;
import com.MacieNhangumele.FeiraAPI.repositories.*;
import com.MacieNhangumele.FeiraAPI.configuration.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ExpositorRepository expositorRepository;
    
    @Autowired
    private VisitanteRepository visitanteRepository;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        
        String userType = null;
        Long entityId = null;
        
        if (user.getRole() == Role.EXPOSITOR) {
            Expositor expositor = expositorRepository.findByUserId(user.getId());
            userType = "EXPOSITOR";
            entityId = expositor.getId();
        } else if (user.getRole() == Role.VISITANTE) {
            Visitante visitante = visitanteRepository.findByUserId(user.getId());
            userType = "VISITANTE";
            entityId = visitante.getId();
        }
        
        return ResponseEntity.ok(new LoginResponseDTO(
            token, 
            user.getId(), 
            user.getRole().name(),
            userType,
            entityId
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (userRepository.existsByEmail(data.email())) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        
        User newUser = new User();
        newUser.setEmail(data.email());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(data.role());
        User savedUser = userRepository.save(newUser);
        
        if (data.role() == Role.EXPOSITOR) {
            Expositor expositor = new Expositor();
            expositor.setUser(savedUser);
            expositor.setNome(data.nome());
            expositor.setTipo(data.tipo());
            expositor.setLinkStandOnline(data.linkStandOnline());
            expositor.setNumeroStandFisico(data.numeroStandFisico());
            expositorRepository.save(expositor);
        } else if (data.role() == Role.VISITANTE) {
            Visitante visitante = new Visitante();
            visitante.setUser(savedUser);
            visitante.setNome(data.visitanteNome());
            visitante.setTipoAcesso(data.tipoAcesso());
            visitanteRepository.save(visitante);
        }
        
        return ResponseEntity.ok().build();
    }
}