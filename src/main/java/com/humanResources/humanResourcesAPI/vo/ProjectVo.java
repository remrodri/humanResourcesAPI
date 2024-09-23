package com.humanResources.humanResourcesAPI.vo;

import com.humanResources.humanResourcesAPI.model.entity.Employee;

import java.util.Date;
import java.util.Set;

public record ProjectVo(
        Long id,
        String title,
        String descr,
        Date startDate,
        Date endDate,
        Set<Employee>employees
) {
}
