package com.ceste.timetrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceste.timetrack.dto.CheckInRequestDTO;
import com.ceste.timetrack.dto.CheckInResponseDTO;
import com.ceste.timetrack.service.ClockInService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/date")
public class ClockInController {
    
    private final ClockInService clockInService;

    //Constructor: Lo utilizamos para injectar el servicio y poderlo utilizar en esta clase
    public ClockInController (ClockInService clockInService){
        this.clockInService = clockInService;
    }

    /* 
        Método para insertar un fichaje.
        @PostMapping sirve para indicarle que va a ser un endpoint de tipo POST.
        Utilizamos la llamada al servicio en el return ya que lo que nos devuelve el servicio es la información que necesitamos
        devolver al frontend y de esta manera nos evitamos crear una variable intermedia.
    */
    @PostMapping("/checkIn")
    public ResponseEntity<CheckInResponseDTO> checkIn(@Valid @RequestBody CheckInRequestDTO req){
        return this.clockInService.checkIn(req);
    }
}
