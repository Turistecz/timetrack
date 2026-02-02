package com.ceste.timetrack.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceste.timetrack.model.ClockIn;

@Repository
public interface ClockInRepository extends JpaRepository<ClockIn, Integer> {
    
    @Query("SELECT opened FROM clock_in WHERE check_in = CAST(?2 AS DATE) AND id_employee = ?1")
    boolean comprobarCheckIn(int idEmployee, LocalDateTime date);
}
