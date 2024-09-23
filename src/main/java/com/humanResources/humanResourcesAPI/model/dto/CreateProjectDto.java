package com.humanResources.humanResourcesAPI.model.dto;

import com.humanResources.humanResourcesAPI.model.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

public record CreateProjectDto(
    @NotBlank(message = "El titulo no debe estar vacio")
//    @Size(min=1,max=50,message = "El titulo debe estar entre 1 y 25 caracteres")
    String title,

    @NotBlank(message = "La descripcion no puede estar vacio")
    String descr,

    @NotNull(message = "La fecha de inicio no puede estar vacia")
    Date startDate,

    @NotNull(message = "la fecha final no puede estar vacia")
    Date endDate,

    Set<Long>employeeIds
//    Set<Employee>employees

) {
}
