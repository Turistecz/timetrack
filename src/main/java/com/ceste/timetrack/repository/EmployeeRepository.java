package com.ceste.timetrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ceste.timetrack.model.Employee;

@Repository


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email); //Comprobar si existe por email.

    @Query(value= "SELECT * FROM employee e WHERE e.email = ?1 AND e.password = ?2", nativeQuery=true)
    Employee findByEmailAndPassword(String email, String password);

    

}


