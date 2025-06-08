package com.MacieNhangumele.FeiraAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints públicos
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        
                        // Feiras - acesso público para leitura
                        .requestMatchers(HttpMethod.GET, "/feiras").permitAll()
                        .requestMatchers(HttpMethod.GET, "/feiras/{id}").permitAll()
                        
                        // Estandes - acesso público para leitura
                        .requestMatchers(HttpMethod.GET, "/estandes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/estandes/{id}").permitAll()
                        
                        // Visitantes
                        .requestMatchers(HttpMethod.GET, "/visitantes").hasRole("EXPOSITOR")
                        .requestMatchers(HttpMethod.GET, "/visitantes/{id}").hasAnyRole("VISITANTE", "EXPOSITOR")
                        .requestMatchers(HttpMethod.DELETE, "/visitantes/{id}").hasAnyRole("VISITANTE", "EXPOSITOR")
                        
                        // Expositores
                        .requestMatchers(HttpMethod.GET, "/expositores").permitAll()
                        .requestMatchers(HttpMethod.GET, "/expositores/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/expositores/{id}").hasAnyRole("EXPOSITOR")
                        
                        // Inscrições
                        .requestMatchers(HttpMethod.GET, "/inscricoes").hasRole("EXPOSITOR")
                        .requestMatchers(HttpMethod.GET, "/inscricoes/{id}").hasAnyRole("VISITANTE", "EXPOSITOR")
                        .requestMatchers(HttpMethod.POST, "/inscricoes").hasRole("VISITANTE")
                        .requestMatchers(HttpMethod.GET, "/inscricoes/feira/{feiraId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/inscricoes/visitante/{visitanteId}").hasAnyRole("VISITANTE", "EXPOSITOR")
                        .requestMatchers(HttpMethod.DELETE, "/inscricoes/{id}").hasAnyRole("VISITANTE", "EXPOSITOR")
                        
                        // Operações de criação/atualização/exclusão para EXPOSITOR
                        .requestMatchers(HttpMethod.POST, "/feiras").hasRole("EXPOSITOR")
                        .requestMatchers(HttpMethod.PUT, "/feiras/{id}").hasRole("EXPOSITOR")
                        .requestMatchers(HttpMethod.DELETE, "/feiras/{id}").hasRole("EXPOSITOR")
                        
                        .requestMatchers(HttpMethod.POST, "/estandes").hasRole("EXPOSITOR")
                        .requestMatchers(HttpMethod.PUT, "/estandes/{id}").hasRole("EXPOSITOR")
                        .requestMatchers(HttpMethod.DELETE, "/estandes/{id}").hasRole("EXPOSITOR")
                        
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}