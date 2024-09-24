package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.dto.CreateEmployeeDto;
import com.humanResources.humanResourcesAPI.model.dto.UpdateEmployeeDto;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.vo.EmployeeVo;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Optional<EmployeeVo> getEmployeeById(Long id);
    Optional<Employee> findEmployeeById(Long id);
    List<EmployeeVo> getAllEmployee();
    EmployeeVo createEmployee(CreateEmployeeDto dto);
    EmployeeVo updateEmployee(Long id, UpdateEmployeeDto dto);
    boolean deleteEmployee(Long id);
    EmployeeVo assignProjectToEmployee(Long employeeId, Long projectId);
}
