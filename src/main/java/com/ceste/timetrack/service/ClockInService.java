package com.ceste.timetrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ceste.timetrack.dto.CheckInRequestDTO;
import com.ceste.timetrack.dto.CheckInResponseDTO;
import com.ceste.timetrack.model.ClockIn;
import com.ceste.timetrack.model.Employee;
import com.ceste.timetrack.repository.ClockInRepository;
import com.ceste.timetrack.repository.EmployeeRepository;

@Service
public class ClockInService {
    
    private final EmployeeRepository employeeRepository;
    private final ClockInRepository clockInRepository;

    public ClockInService(EmployeeRepository employeeRepository, ClockInRepository clockInRepository){
        this.employeeRepository = employeeRepository;
        this.clockInRepository = clockInRepository;
    }

    public ResponseEntity<CheckInResponseDTO> checkIn(CheckInRequestDTO checkInDTO){
        /* 
            Llamamos a la funcion findById del repositorio para obtener el empleado que nos llega desde el endpoint (a través de su ID). 
            En caso de que no encuentre al empleado, lanza un error.
        */
        Employee employee = this.employeeRepository.findById(checkInDTO.getIdEmployee())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Empleado no encontrado"
                ));

        /*
            Si el empleado no está activo, lanzamos un error. Utilizamos el ! como signo de negación.
        */
        if(!employee.isActive()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El empleado no está activo");
        }

        /*
            Llamamos a comprobarCheckIn para revisar si el fichaje ya está abierto. 
            Para la comprobación, comparamos que no haya ningún fichaje en la fecha actual, y como utilizamos DateTime, necesitamos
            comparar con un rango de fechas. Desde hoy a las 00:00:00 hasta mañana a las 00:00:00.        
        */
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        boolean clockInAlreadySubmitted = this.clockInRepository.comprobarCheckIn(checkInDTO.getIdEmployee(), start, end);

        /*
            Si el fichaje está abierto, lanzamos un error.
        */
        if(clockInAlreadySubmitted){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya han un fichaje abierto para este empleado");
        }
        
        /*
            Si todas las validaciones anteriores son correctas, utilizamos el método save del repository para insertar el fichaje
            en la base de datos. 
        */
        LocalDateTime dateCheckIn = LocalDateTime.now();
        ClockIn clockIn = new ClockIn();
        clockIn.setEmployee(employee);
        clockIn.setCheckIn(dateCheckIn);
        clockIn.setOpened(true);
        clockIn.setNotes(null);
        clockInRepository.save(clockIn);


        /*
            Utilizamos el método obtenerCheckIn del repository para obtener el fichaje que acabamos de crear en el bloque anterior.
            Una vez hemos recuperado el fichaje, se lo asignamos al DTO de respuesta, que va a ser la información que necesita el
            frontend.
        */
        ClockIn currentCheckIn = this.clockInRepository.obtenerCheckIn(checkInDTO.getIdEmployee(), start, end);        
        CheckInResponseDTO responseDTO = new CheckInResponseDTO();
        responseDTO.setIdClockIn(currentCheckIn.getId());
        responseDTO.setIdEmployee(currentCheckIn.getEmployee().getId());
        responseDTO.setCheckIn(currentCheckIn.getCheckIn());
        responseDTO.setNotes(currentCheckIn.getNotes());


        /*
            Devolvemos tanto el fichaje que acabamos de crear como el status de HTTP indicando que la creación del fichaje ha sido
            satisfactoria.
            Utilizamos ResponseEntity para poder devolver ambas cosas a la vez.
        */
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
