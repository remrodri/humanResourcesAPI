package com.humanResources.humanResourcesAPI.controller;

import com.humanResources.humanResourcesAPI.model.dto.CreateEmployeeDto;
import com.humanResources.humanResourcesAPI.service.EmployeeService;
import com.humanResources.humanResourcesAPI.vo.EmployeeVo;
import com.humanResources.humanResourcesAPI.vo.StandarResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?>findAllDepartments(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findEmployeeById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<StandarResponse>createEmployee(@Valid @RequestBody CreateEmployeeDto dto){
        EmployeeVo vo = employeeService.createEmployee(dto);
        StandarResponse response = StandarResponse
                .builder()
                .message("Empleado creado correctamente")
                .data(vo)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
