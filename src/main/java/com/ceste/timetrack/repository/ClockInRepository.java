package com.ceste.timetrack.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceste.timetrack.model.ClockIn;

@Repository
public interface ClockInRepository extends JpaRepository<ClockIn, Integer> {
    
    /*
        Las triples comillas se utilizan para poder poner multilinea en Java.
    */
    @Query(value = """
        SELECT COUNT(c) > 0 
        FROM clock_in c 
        WHERE check_in >= ?2 AND check_in < ?3 AND id_employee = ?1
    """, nativeQuery = true)
    boolean comprobarCheckIn(int idEmployee, LocalDateTime start, LocalDateTime end);

    @Query(value = """
        SELECT c
        FROM clock_in c 
        WHERE check_in >= ?2 AND check_in < ?3 AND id_employee = ?1
    """, nativeQuery = true)
    ClockIn obtenerCheckIn(int idEmployee, LocalDateTime start, LocalDateTime end);
}
