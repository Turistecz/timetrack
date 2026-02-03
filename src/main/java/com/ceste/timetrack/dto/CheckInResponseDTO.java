package com.ceste.timetrack.dto;

import java.time.LocalDateTime;

public class CheckInResponseDTO{
    
    private int idClockIn;
    private int idEmployee;
    private LocalDateTime checkIn;
    private String notes;

    public int getIdClockIn() {
        return idClockIn;
    }

    public void setIdClockIn(int idClockIn) {
        this.idClockIn = idClockIn;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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