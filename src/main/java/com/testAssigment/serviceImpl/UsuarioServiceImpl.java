/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testAssigment.serviceImpl;

import com.testAssigment.dto.request.LoginRequestDTO;
import com.testAssigment.dto.request.RegistroRequestDTO;
import com.testAssigment.dto.response.RegistroResponseDTO;
import com.testAssigment.entity.Usuario;
import com.testAssigment.repository.UsuarioRepository;
import com.testAssigment.service.UsuarioService;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Admin
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final JavaMailSender emailSender;

    @Override
    @Transactional
    public void registrar(RegistroRequestDTO registroDTO) {
        // Verificar si ya existe un usuario con el email proporcionado
        if (usuarioRepository.findByemail(registroDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setApellido(registroDTO.getApellido());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(registroDTO.getPassword());

        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean autenticar(LoginRequestDTO loginDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByemail(loginDTO.getEmail());
        return usuarioOptional.isPresent() && usuarioOptional.get().getPassword().equals(loginDTO.getPassword());
    }

    @Override
    public void restablecerContrasenia(String email) {
        // Generar una nueva contraseña aleatoria
        String nuevaContrasenia = generarContrasenaAleatoria();

        // Buscar el usuario por su email
        Usuario usuario = usuarioRepository.findByemail(email)
                .orElseThrow(() -> new RuntimeException("No se encontró un usuario con el email proporcionado."));

        // Actualizar la contraseña del usuario en la base de datos
        usuario.setPassword(nuevaContrasenia);
        usuarioRepository.save(usuario);

        // Enviar la nueva contraseña por correo
        enviarCorreo(usuario.getEmail(), nuevaContrasenia);
    }

    private String generarContrasenaAleatoria() {

        int longitud = 8;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$%&*!";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        boolean tieneLetraMayuscula = false;
        boolean tieneLetraMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneCaracterEspecial = false;

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            char caracter = caracteres.charAt(indice);
            password.append(caracter);

            if (Character.isUpperCase(caracter)) {
                tieneLetraMayuscula = true;
            } else if (Character.isLowerCase(caracter)) {
                tieneLetraMinuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            } else if ("@#$%&*!".indexOf(caracter) != -1) {
                tieneCaracterEspecial = true;
            }
        }

        // Verificar que la contraseña contenga al menos un carácter de cada tipo requerido
        if (!tieneLetraMayuscula || !tieneLetraMinuscula || !tieneNumero || !tieneCaracterEspecial) {
            // Llamar recursivamente si no cumple con los requisitos
            return generarContrasenaAleatoria();
        }

        return password.toString();
    }

    private void enviarCorreo(String email, String nuevaContrasena) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Nueva contraseña");
        message.setText("Tu nueva contraseña es: " + nuevaContrasena);
        emailSender.send(message);
    }
}
