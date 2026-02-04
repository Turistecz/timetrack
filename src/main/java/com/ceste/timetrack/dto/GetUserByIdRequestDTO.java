package com.ceste.timetrack.dto;

import jakarta.validation.constraints.NotNull;

public class GetUserByIdRequestDTO {
  @NotNull(message = "El id del empleado es obligatorio")
  private int idEmployee;

  public int getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  }

}
