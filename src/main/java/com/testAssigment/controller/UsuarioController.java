/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testAssigment.controller;

import com.testAssigment.dto.request.LoginRequestDTO;
import com.testAssigment.dto.request.RegistroRequestDTO;
import com.testAssigment.dto.response.LoginResponseDTO;
import com.testAssigment.dto.response.RegistroResponseDTO;
import com.testAssigment.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Admin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody @Valid RegistroRequestDTO registroDTO) {
        try {
            usuarioService.registrar(registroDTO);
            RegistroResponseDTO registroResponseDTO = new RegistroResponseDTO("Usuario creado");

            return ResponseEntity.status(HttpStatus.CREATED).body(registroResponseDTO);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        if (usuarioService.autenticar(loginDTO)) {
            return ResponseEntity.ok(new RegistroResponseDTO("Inicio de sesión exitoso"));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario/contraseña incorrectos");
        }
    }

    @PostMapping("/restablecer-contrasena")
    public ResponseEntity<String> restablecerContrasena(@RequestParam String email) {
        try {
            usuarioService.restablecerContrasenia(email);
            return ResponseEntity.ok("Se ha generado una nueva clave para tu usuario. Podrás visualizarla en tu mail registrado.");
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
