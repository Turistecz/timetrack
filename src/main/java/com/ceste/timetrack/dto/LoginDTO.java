package com.ceste.timetrack.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {

    @Email(message = "El correo no es válido")
    @NotNull(message=" El email del Empleado no puede ser nulo")
    private String email;

    @NotNull(message=" La contrsaeña del Empleado no puede ser nulo")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}