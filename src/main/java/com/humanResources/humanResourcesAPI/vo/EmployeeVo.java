package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.model.entity.Project;

import java.util.Date;
import java.util.Set;

public record EmployeeVo(
        Long id,
        String firstName,
        String lastName,
        Date birthDate,
        String email,
        String phone,
        Position position,
        Set<Project>projects
) {
}
