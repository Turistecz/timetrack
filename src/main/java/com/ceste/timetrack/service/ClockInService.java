package com.ceste.timetrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceste.timetrack.repository.ClockInRepository;

@Service
public class ClockInService {
 
    @Autowired
    private ClockInRepository clockInRepository;
}
