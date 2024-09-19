package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.dto.CreateDepartmentDto;
import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.vo.DepartmentVo;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    Optional<DepartmentVo> getDepartmentById(Long id);
    Optional<Department> findDepartmentById(Long id);
    List<DepartmentVo> findAllDepartments();
    DepartmentVo createDepartment(CreateDepartmentDto dto);
    DepartmentVo updateDepartmentById(Long id, CreateDepartmentDto dto);
    boolean deleteDepartmentById(Long id);
}
