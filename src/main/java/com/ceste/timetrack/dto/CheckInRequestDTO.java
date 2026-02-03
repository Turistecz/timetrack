package com.ceste.timetrack.dto;

import jakarta.validation.constraints.NotNull;

public class CheckInRequestDTO {

    @NotNull(message=" El id Empleado no puede ser nulo")
    private int idEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}