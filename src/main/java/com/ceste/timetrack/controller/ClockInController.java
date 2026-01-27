package com.ceste.timetrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ceste.timetrack.service.ClockInService;

@RestController
public class ClockInController {
    
    @Autowired
    private ClockInService clockInService;
}
