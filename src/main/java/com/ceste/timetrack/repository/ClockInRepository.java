package com.ceste.timetrack.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceste.timetrack.model.ClockIn;

@Repository
public interface ClockInRepository extends JpaRepository<ClockIn, Integer> {


    /*Consultas que realizamos a la BBDD 
        Las triples comillas se usan para poder poner multinea en java */
    @Query("SELECT count(c) > 0 FROM clock_in c WHERE check_in >= ?2AND check_in < ?3 AND idEmployee = ?1")
    boolean validCheckIn(int idEmployee, LocalDateTime start, LocalDateTime end);

    @Query("""
            SELECT c
            FROM clock_in c 
            WHERE check_in >= ?2AND check_in < ?3 AND idEmployee = ?1
            """)
    ClockIn getCheckIn(int idEmployee, LocalDateTime start, LocalDateTime end);
}
