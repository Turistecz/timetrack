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

    //Este es el constructor, primer método que debemos tener o llamar, e inyectar los servicios que utilizaremos
    public ClockInController (ClockInService clockInService) { 
        this.clockInService = clockInService;
    };

    /*
        @ Notacion para indicar el endpont tipo POST
        Método para insertar un fichaje   
    */ 
    @PostMapping("/checkIn")
    public ResponseEntity<CheckInResponseDTO> checkIn(@Valid @RequestBody CheckInRequestDTO req){
       return this.clockInService.checkIn(req);    // devuelvo directamente el servicio porque ya en el Servicio devuelve el respondeEntity y evitamos usar una variable intermedia.
    };

}