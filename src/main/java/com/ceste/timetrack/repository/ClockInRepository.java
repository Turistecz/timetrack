package com.ceste.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceste.timetrack.model.ClockIn;

public interface ClockInRepository extends JpaRepository<ClockIn, Integer> {
    
}
