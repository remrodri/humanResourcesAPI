package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Employee;

import java.util.Set;

public record DepartmenEmployeesVo(
        Set<Employee>employees
) {
}
