package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.model.entity.Employee;

import java.util.Set;

public record PositionVo(
    Long id,
    String name,
    String descr,
    Double baseSalary,
    Department department,
    Set<Employee>employees
) {
}
