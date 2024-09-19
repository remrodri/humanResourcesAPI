package com.humanResources.humanResourcesAPI.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public record CreateDepartmentDto(
@NotBlank(message = "El nombre no debe estar vacio")
@Size(min = 1,max = 50,message = "El nombre debe estar entre 1 y 50 caracteres")
@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre solo puede contener letras y números")
String name,

@NotBlank(message="La descr no debe estar vacia")
@Size(min = 1,max=500,message = "La descripcion debe estar entre 1c y 500 caracteres")
@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "La descr solo puede contener numeros y letras")
String descr

//Set<Long> positionIds
) {
}
