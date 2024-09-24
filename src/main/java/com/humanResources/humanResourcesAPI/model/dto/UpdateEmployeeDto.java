package com.humanResources.humanResourcesAPI.model.dto;

import com.humanResources.humanResourcesAPI.model.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record UpdateEmployeeDto(
        @NotBlank(message = "El nombre no debe estar vacio")
        String firstName,

        @NotBlank(message = "El apellido no debe estar vacio")
        String lastName,

        @NotNull(message = "La fecha de nacimiento no debe estar vacio")
        Date birthDate,

        @NotBlank(message = "El email no debe estar vacio")
        String email,

        @NotBlank(message = "El numero de telefono no debe estar vacio")
        String phone,

        @NotNull(message = "Debe agregar el id de un puesto")
        Long positionId,

        List<Long>projects
) {
}
