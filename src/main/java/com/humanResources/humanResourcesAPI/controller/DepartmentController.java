package com.humanResources.humanResourcesAPI.controller;

import com.humanResources.humanResourcesAPI.model.dto.CreateDepartmentDto;
import com.humanResources.humanResourcesAPI.service.DepartmentService;
import com.humanResources.humanResourcesAPI.service.PositionService;
import com.humanResources.humanResourcesAPI.vo.DepartmenEmployeesVo;
import com.humanResources.humanResourcesAPI.vo.DepartmentVo;
import com.humanResources.humanResourcesAPI.vo.StandarResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final PositionService positionService;

    public DepartmentController(DepartmentService departmentService, PositionService positionService) {
        this.departmentService = departmentService;
        this.positionService = positionService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findDepartmentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findDepartmentVoById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAllDepartments() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.findAllDepartments());
    }

    @PostMapping
    public ResponseEntity<StandarResponse>createDepartment(@Valid @RequestBody CreateDepartmentDto dto){
        DepartmentVo departmentVo = departmentService.createDepartment(dto);
        StandarResponse standarResponse = StandarResponse
                .builder()
                .message("Departamento creado correctamente")
                .data(departmentVo)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(standarResponse);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<StandarResponse>findEmployeesByDepartmentId(@PathVariable("id") Long id){
        DepartmenEmployeesVo departmenEmployeesVo = departmentService.getDepartmentEmployeesById(id);
        StandarResponse standarResponse = StandarResponse
                .builder()
                .message("Empleados encontrados correctamente")
                .data(departmenEmployeesVo)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(standarResponse);
    }
}
