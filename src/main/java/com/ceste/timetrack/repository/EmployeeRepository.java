package com.ceste.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ceste.timetrack.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query("SELECT e FROM employee e WHERE e.email = ?1 AND e.password = ?2")
    Employee findByEmailAndPassword(String email, String password);

}