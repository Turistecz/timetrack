package com.ceste.timetrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ceste.timetrack.dto.CheckInRequestDTO;
import com.ceste.timetrack.service.ClockInService;
import com.ceste.timetrack.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/date")
public class ClockInController {
    
    private final ClockInService clockInService;

    public ClockInController (ClockInService clockInService){
        this.clockInService = clockInService;
    }


    @PostMapping("/checkIn")
    public CheckInRequestDTO checkIn(@Valid @RequestBody CheckInRequestDTO req){
        this.clockInService.checkIn(req);
        return req;
    }
}
