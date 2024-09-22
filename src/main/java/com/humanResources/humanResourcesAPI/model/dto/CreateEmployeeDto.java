package com.humanResources.humanResourcesAPI.model.dto;

import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.model.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public record CreateEmployeeDto(
        @NotBlank(message = "El nombre no debe estar vacio")
        @Size(min=1,max=50,message = "El nombre debe estar entre 1 y 25 caracteres")
        @Pattern(regexp = "^[a-zA-Z]+$",message = "El nombre solo puede contener letras")
        String firstName,

        @NotBlank(message = "El apellido no debe estar vacio")
        @Size(min=1,max=50,message = "El apellido debe estar entre 1 y 50 caracteres")
        @Pattern(regexp = "^[a-zA-Z]+$",message = "El apellido solo puede contener letras")
        String lastName,

        @NotNull(message = "La fecha de nacimiento no debe estar vacio")
        Date birthDate,

        @NotBlank(message = "El emial no debe estar vacio")
//        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n", message = "Se debe respetar el formato de email")
        String email,

        @NotBlank(message = "El numero de telefono no debe estar vacio")
//        @Pattern(regexp = "^\\d+$\n",message = "El numero de telefono solo deben ser numeros")
        String phone,

        @NotNull(message = "La posicion no debe estar vacio")
        Long position
//        List<Project>projects
) {
}
