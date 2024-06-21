/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.testAssigment.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class LoginRequestDTO {
        @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
            @NotBlank(message = "La contraseña no puede estar vacía")
    
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un símbolo"
    )
    private String password;

   
    
    
}
