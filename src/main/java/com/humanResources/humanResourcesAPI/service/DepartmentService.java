package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.Exception.DepartmentNotFoundException;
import com.humanResources.humanResourcesAPI.model.Exception.PositionNotFoundException;
import com.humanResources.humanResourcesAPI.model.dto.CreateDepartmentDto;
import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.repository.IDepartmentRepository;
import com.humanResources.humanResourcesAPI.repository.IPositionRepository;
import com.humanResources.humanResourcesAPI.vo.DepartmenEmployeesVo;
import com.humanResources.humanResourcesAPI.vo.DepartmentVo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService implements IDepartmentService {
private final IDepartmentRepository departmentRepository;
private final IPositionRepository positionRepository;
public DepartmentService(IDepartmentRepository departmentRepository, IPositionRepository positionRepository) {
    this.departmentRepository = departmentRepository;
    this.positionRepository = positionRepository;
}

    @Override
    public Optional<DepartmentVo> findDepartmentVoById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()){
            throw new DepartmentNotFoundException("No se encontro el Departamento con el id: "+id);
        }
        DepartmentVo vo= new DepartmentVo(
                department.get().getId(),
                department.get().getName(),
                department.get().getDescr(),
                department.get().getPositions()
        );

        return Optional.of(vo);
    }

    @Override
    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<DepartmentVo> findAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
                return departments.stream()
                .map(department -> new DepartmentVo(
                        department.getId(),
                        department.getName(),
                        department.getDescr(),
                        department.getPositions()
                ))
                .toList();
    }

    @Override
    public DepartmentVo createDepartment(CreateDepartmentDto dto) {
        Department department = new Department();
        department.setName(dto.name());
        department.setDescr(dto.descr());
        department.setPositions(Collections.emptySet());

        Department departmentSaved = departmentRepository.save(department);
        return new DepartmentVo(
        departmentSaved.getId(),
        departmentSaved.getName(),
        departmentSaved.getDescr(),
        departmentSaved.getPositions()
    );
}

    @Override
    public DepartmentVo updateDepartmentById(Long id, CreateDepartmentDto dto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            throw new DepartmentNotFoundException("No se encontro el departamento con id: "+id);
        }
        return null;
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        return false;
    }

    @Override
    public DepartmenEmployeesVo getDepartmentEmployeesById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()){
            throw new DepartmentNotFoundException("No se encontro el departamento con id: "+id);
        }
        Set<Position> positions = optionalDepartment.get().getPositions();
        Set<Employee> employees = new HashSet<>();
        for (Position position : positions){
            employees.addAll(position.getEmployees());
        }
        return new DepartmenEmployeesVo(
                employees
        );
    }
}
