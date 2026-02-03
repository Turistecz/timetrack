package com.ceste.timetrack.dto;

import com.ceste.timetrack.model.EnumRol;

public class GetUserByIdResponseDTO {
  private int idEmployee;
  private String name;
  private String email;
  private String password;
  private Enum<EnumRol> rol;
  private boolean active;

  public int getIdEmployee() {
    return idEmployee;
  }
  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  }
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
  public Enum<EnumRol> getRol() {
    return rol;
  }
  public void setRol(Enum<EnumRol> rol) {
    this.rol = rol;
  }
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
}
