package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.Exception.PositionNotFoundException;
import com.humanResources.humanResourcesAPI.model.dto.CreateDepartmentDto;
import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.repository.IDepartmentRepository;
import com.humanResources.humanResourcesAPI.repository.IPositionRepository;
import com.humanResources.humanResourcesAPI.vo.DepartmentVo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentService implements IDepartmentService {
private final IDepartmentRepository departmentRepository;
private final IPositionRepository positionRepository;
public DepartmentService(IDepartmentRepository departmentRepository, IPositionRepository positionRepository) {
    this.departmentRepository = departmentRepository;
    this.positionRepository = positionRepository;
}

    @Override
    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentVo createDepartment(CreateDepartmentDto dto) {
        Set<Position>positions = new HashSet<>();
//        for (Long positionId:dto.positionIds()){
//            Optional<Position>positionOpcional = positionRepository.findById(positionId);
//            if(positionOpcional.isEmpty()){
//                throw new PositionNotFoundException("No se encontro el puesto con el id "+positionId);
//            }
//            positions.add(positionOpcional.get());
//        }
        Department department = new Department();
        department.setName(dto.name());
        department.setDescr(dto.descr());
        department.setPositions(positions);

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
            throw new PositionNotFoundException("No se encontro el departamento con id: "+id);
        }


        return null;
    }

    @Override
    public boolean deleteDepartmentById(Long id) {
        return false;
    }
}
