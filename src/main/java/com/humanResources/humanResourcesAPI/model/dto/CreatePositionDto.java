package com.humanResources.humanResourcesAPI.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePositionDto(
        @NotBlank(message = "El nombre no debe estar vacio")
        @Size(min = 1, max = 50, message = "el nombre debe estar entre 1 y 50 caracteres")
        @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre solo puede contener letras y números")
        String name,

        @NotBlank(message = "La descripcion no debe estar vacio")
        @Size(min = 1,max=500, message= "La descripcion debe estar entre 1 y 500 caracteres")
        String descr,

        BigDecimal baseSalary,
        long departmentId
) {
}
