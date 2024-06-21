/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testAssigment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequestDTO {

    @NotBlank(message = "El nombre no debe estar en blanco o nulo")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s']{1,30}$", message = "El nombre completo debe contener solo letras y tener un máximo de 30 caracteres")
    private String nombre;
    @NotBlank(message = "El apellido no debe estar en blanco o nulo")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s']{1,30}$", message = "El apellido debe contener solo letras y tener un máximo de 30 caracteres")
    private String apellido;
    @NotBlank(message = "El correo electronico no debe estar en blanco o nulo")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "La dirección de correo electrónico no es válida")
    private String email;
    @NotBlank(message = "La contrase\u00F1a no debe estar en blanco o nulo")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,15}$", message = "La contrase\u00F1a no cumple con los requisitos. Debe tener al menos una letra mayúscula, una minúscula, un número y un símbolo, y que no sea menor a 8 caracteres.")
    private String password;

    
    
    
}
