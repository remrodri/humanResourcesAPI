package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Employee;

import java.util.List;
import java.util.Set;

public record PositionEmployeesVo(
        Set<Employee> employees
) {
}
