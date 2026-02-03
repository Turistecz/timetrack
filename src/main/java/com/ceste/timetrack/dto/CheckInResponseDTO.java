package com.ceste.timetrack.dto;

import java.time.LocalDateTime;

public class CheckInResponseDTO {


    private int idClockin;
    private int idEmpleado;
    private LocalDateTime checkIn;
    private String notes;


    public int getIdClockin() {
        return idClockin;
    }
    public void setIdClockin(int idClockin) {
        this.idClockin = idClockin;
    }
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public LocalDateTime getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
     
}
