package com.ceste.timetrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    // Repositorio para acceder a los datos de empleados
    private final EmployeeRepository employeeRepository;

    // Repositorio para acceder a los fichajes (clock-in)
    private final ClockInRepository clockInRepository;

    // Inyección de dependencias por constructor
    public ClockInService(EmployeeRepository employeeRepository, ClockInRepository clockInRepository) {
        this.employeeRepository = employeeRepository;
        this.clockInRepository = clockInRepository;
    }

    /*
     * Método principal para registrar el fichaje de entrada de un empleado
     * Recibe un DTO con los datos necesarios y devuelve un DTO de respuesta
     */
    public ResponseEntity<CheckInResponseDTO> checkIn(CheckInRequestDTO checkInDTO){

        // 1️⃣ VALIDACIÓN: Buscar el empleado por ID para obtener al empleado que nos llega desde el endpoint.
        // Si no existe, se lanza un error 404 (NOT FOUND)
        Employee employee = this.employeeRepository.findById(checkInDTO.getIdEmpleado())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Empleado no encontrado"));

        // 2️⃣ VALIDACIÓN: Comprobar si el empleado está activo
        // Si está inactivo, no se permite fichar (403 FORBIDDEN)
        if(!employee.isActive()) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "Empleado inactivo, no puede registrar entrada");
        }

        // 3️⃣ VALIDACIÓN: Comprobar si ya ha hecho check-in hoy
        /*  Definimos el rango de fechas: desde el inicio de hoy hasta el inicio de mañana
            y como usamos DATETIME necesitamos comparar con rangos desde 00:00 de hoy hasta 00:00 de mañana
        */
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        // Consultamos si existe un fichaje abierto para hoy
        boolean clockInAlreadySubmitted =
                this.clockInRepository.validCheckIn(checkInDTO.getIdEmpleado(), start, end);

        // Si ya existe un fichaje, se lanza un error 409 (CONFLICT)
        if(clockInAlreadySubmitted){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Error, ya hay un fichaje abierto para este empleado"
            );
        }

        // 4️⃣ REGISTRO DEL CHECK-IN
        // Se obtiene la fecha y hora actual del sistema
        LocalDateTime dateCheckIn = LocalDateTime.now();

        // Creamos el objeto ClockIn que se guardará en la base de datos
        ClockIn clockIn = new ClockIn();
        clockIn.setEmployee(employee);     // Asociamos el empleado
        clockIn.setCheckIn(dateCheckIn);   // Hora de entrada
        clockIn.setOpened(true);           // El fichaje queda abierto
        clockIn.setNotes(null);            // Notas vacías por defecto

        // Guardamos el fichaje en la base de datos
        clockInRepository.save(clockIn);

        // 5️⃣ RECUPERACIÓN DEL FICHAJE CREADO
        // Obtenemos el fichaje del empleado para el día actual desde el repositorio 
        ClockIn currentCheckIn =
                this.clockInRepository.getCheckIn(checkInDTO.getIdEmpleado(), start, end);

        // 6️⃣ CONSTRUCCIÓN DEL DTO DE RESPUESTA
        /*  Se mapean los datos del modelo a un DTO y pasamos por parametros los valores de 
            currentCheckIn que será la informaciómn que necesita el frontend
        */
        CheckInResponseDTO responseDTO = new CheckInResponseDTO();
        responseDTO.setIdClockin(currentCheckIn.getId());
        responseDTO.setIdEmpleado(currentCheckIn.getEmployee().getId());
        responseDTO.setCheckIn(currentCheckIn.getCheckIn());
        responseDTO.setNotes(currentCheckIn.getNotes());

        
        // 7️⃣ RESPUESTA FINAL
        // Se devuelve un HTTP 201 (CREATED) con el DTO en el body
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
