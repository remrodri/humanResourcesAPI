package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.Exception.PositionNotFoundException;
import com.humanResources.humanResourcesAPI.model.dto.CreatePositionDto;
import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.repository.IPositionRepository;
import com.humanResources.humanResourcesAPI.vo.PositionVo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PositionService implements  IPositionService{
    private final IPositionRepository positionRepository;
    private final IDepartmentService departmentService;

    public PositionService(IPositionRepository positionRepository, IDepartmentService departmentService) {
        this.positionRepository = positionRepository;
        this.departmentService = departmentService;
    }

    @Override
    public Optional<Position> findPositionById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public List<Position> findAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public PositionVo createPosition(CreatePositionDto dto) {
        Optional<Department>departmentOptional = departmentService.findDepartmentById(dto.departmentId());
        Set<Employee>employees = new HashSet<>();

        if(departmentOptional.isEmpty()){
            throw new PositionNotFoundException("No se encontro el departamento con el id " + dto.departmentId());
        }
        Position position = new Position();
        position.setName(dto.name());
        position.setDescr(dto.descr());
        position.setBaseSalary(dto.baseSalary());
        position.setDepartment(departmentOptional.get());
        position.setEmployees(employees);

        Position savedPosition = positionRepository.save(position);

        return new PositionVo(
                savedPosition.getId(),
                savedPosition.getName(),
                savedPosition.getDescr(),
                savedPosition.getBaseSalary(),
                savedPosition.getDepartment(),
                savedPosition.getEmployees()
        );
    }

    @Override
    public PositionVo updatePosition(Long id, CreatePositionDto dto) {
        return null;
    }

    @Override
    public boolean deletePositionById(Long id) {
        return false;
    }
}
