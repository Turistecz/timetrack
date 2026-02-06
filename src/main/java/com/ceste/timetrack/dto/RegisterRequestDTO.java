package com.ceste.timetrack.dto;

import com.ceste.timetrack.model.EnumRol;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {
    
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]{3,50}$",
    message = "El nombre debe tener entre 3 y 50 letras y solo caracteres alfabéticos o espacios")
    private String name;


    @Email(message = "El correo no es válido")
    @NotBlank
    private String email;

    @NotNull(message=" La contrsaeña del Empleado no puede ser nulo")
    @Size(min=8, message="La contraseña debe tener al menos 8 caracteres.") //tamaño minimo de la contraseña
    private String password;

    @NotNull(message=" El rol del Empleado no puede ser nulo")
    private EnumRol rol;

    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public EnumRol getRol() {
        return rol;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    


}
