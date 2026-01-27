package com.ceste.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceste.timetrack.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
