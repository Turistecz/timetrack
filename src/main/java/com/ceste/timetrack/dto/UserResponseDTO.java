package com.ceste.timetrack.dto;

import com.ceste.timetrack.model.EnumRol;


public class UserResponseDTO {

    private String name; 
    private String email;
    private EnumRol rol;

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

    public EnumRol getRol() {
        return rol;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }
    
}
