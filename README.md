# Challenge
# Test Assignment: Registro y Login de Usuario

Este repositorio contiene el código para un proyecto de registro y autenticación de usuarios, desarrollado en Java con Spring Boot y con un frontend básico en HTML, CSS y JavaScript.

## Ramas del Repositorio

El repositorio está organizado en dos ramas principales:
- `back`: Contiene el código del backend desarrollado en Spring Boot.
- `front`: Contiene el código del frontend desarrollado en HTML, CSS y JavaScript.

## Descripción del Proyecto

### Funcionalidades Implementadas

1. **Registro de Usuario**:
   - Permite a los nuevos usuarios registrarse en el sistema proporcionando nombre, apellido, email y contraseña.
   - Incluye validaciones para asegurar que el email contenga el carácter "@" y que la contraseña cumpla con ciertos criterios (letra mayúscula, letra minúscula, número y símbolo, mínimo 8 caracteres).

2. **Inicio de Sesión**:
   - Permite a los usuarios registrados iniciar sesión validando el email y contraseña ingresados.
   - Muestra el mensaje "Usuario/contraseña incorrectos" si alguno de los datos no cumple con los criterios especificados.

3. **Restablecimiento de Contraseña**:
   - Permite a los usuarios restablecer su contraseña enviando una nueva contraseña al email registrado.

### Requerimientos Técnicos

- Java 8 o superior
- Spring Boot
- SQL para la persistencia de datos
- HTML, CSS y JavaScript para el frontend básico
