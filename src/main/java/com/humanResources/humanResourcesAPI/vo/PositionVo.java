package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.model.entity.Employee;

import java.math.BigDecimal;
import java.util.Set;

public record PositionVo(
    Long id,
    String name,
    String descr,
    BigDecimal baseSalary,
    Department department,
    Set<Employee>employees
) {
}
