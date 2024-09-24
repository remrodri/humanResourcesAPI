package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.Exception.PositionNotFoundException;
import com.humanResources.humanResourcesAPI.model.dto.CreatePositionDto;
import com.humanResources.humanResourcesAPI.model.entity.Department;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.repository.IPositionRepository;
import com.humanResources.humanResourcesAPI.vo.PositionVo;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public PositionVo findPositionVoById(Long id) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        if (positionOptional.isEmpty()){
            throw new PositionNotFoundException("No se encontro la posicion con el id: " + id);
        }
        return new PositionVo(
            positionOptional.get().getId(),
            positionOptional.get().getName(),
            positionOptional.get().getDescr(),
            positionOptional.get().getBaseSalary(),
            positionOptional.get().getDepartment(),
            positionOptional.get().getEmployees()
        );
    }

    @Override
    public List<PositionVo> findAllPositionVo() {
        List<Position> positions = positionRepository.findAll();
        List<PositionVo> positionVos = new ArrayList<>();
        for (Position position : positions) {
            positionVos.add(new PositionVo(
                    position.getId(),
                    position.getName(),
                    position.getDescr(),
                    position.getBaseSalary(),
                    position.getDepartment(),
                    position.getEmployees()
            ));
        }
        return positionVos;
    }

}
