/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.testAssigment.service;

import com.testAssigment.dto.request.LoginRequestDTO;
import com.testAssigment.dto.request.RegistroRequestDTO;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Admin
 */
public interface UsuarioService {
    public void registrar(RegistroRequestDTO registroDTO);
    public boolean autenticar(LoginRequestDTO loginDTO);
    public void restablecerContrasenia(String email);
}
