package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.Exception.EmployeeNotFoundException;
import com.humanResources.humanResourcesAPI.model.Exception.PositionNotFoundException;
import com.humanResources.humanResourcesAPI.model.Exception.ProjectNotFoundException;
import com.humanResources.humanResourcesAPI.model.dto.CreateEmployeeDto;
import com.humanResources.humanResourcesAPI.model.dto.UpdateEmployeeDto;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.model.entity.Project;
import com.humanResources.humanResourcesAPI.repository.IEmployeeRepository;
import com.humanResources.humanResourcesAPI.repository.IProjectRepository;
import com.humanResources.humanResourcesAPI.vo.EmployeeVo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IPositionService positionService;
    private final IProjectRepository projectRepository;

    public EmployeeService(
            IEmployeeRepository employeeRepository,
            IPositionService positionService,
            IProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<EmployeeVo> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("No se encontro el empleado");
        }
        EmployeeVo employeeVo = new EmployeeVo(
                employee.get().getId(),
                employee.get().getFirstName(),
                employee.get().getLastName(),
                employee.get().getBirthDate(),
                employee.get().getEmail(),
                employee.get().getPhone(),
                employee.get().getPosition(),
                employee.get().getProjects()
        );
        return Optional.of(employeeVo);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<EmployeeVo> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> new EmployeeVo(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getBirthDate(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getPosition(),
                        employee.getProjects()
                ))
                .toList();
    }

    @Override
    public EmployeeVo createEmployee(CreateEmployeeDto dto) {
        Optional<Position>positionOptional = positionService.findPositionById(dto.positionId());
        Set<Project>projects = new HashSet<>();

        if (positionOptional.isEmpty()){
            throw new PositionNotFoundException("No se encontro la position");
        }
        Employee employee = new Employee();
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setBirthDate(dto.birthDate());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setPosition(positionOptional.get());
//        employee.setProjects(Collections.emptySet());
        employee.setProjects(projects);

        Employee employeeSaved = employeeRepository.save(employee);

        return new EmployeeVo(
                employeeSaved.getId(),
                employeeSaved.getFirstName(),
                employeeSaved.getLastName(),
                employeeSaved.getBirthDate(),
                employeeSaved.getEmail(),
                employeeSaved.getPhone(),
                employeeSaved.getPosition(),
                employeeSaved.getProjects()
        );
    }

    @Override
    public EmployeeVo updateEmployee(Long id, UpdateEmployeeDto dto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new EmployeeNotFoundException("No se encontro el empleado con el id: "+id);
        }

        Optional<Position> position = positionService.findPositionById(dto.positionId());
        if (position.isEmpty()) {
            throw new PositionNotFoundException("No se encontro la position con el id: "+dto.positionId());
        }

        Set<Project>projects = new HashSet<>();
        for (Long projectId: dto.projects()){
            Optional<Project> project = projectRepository.findById(projectId);

            if (project.isEmpty()) {
                throw new ProjectNotFoundException("No se encontro el project");
            }
            projects.add(project.get());
        }

        Employee employeeToUpdate = employeeOptional.get();
        employeeToUpdate.setFirstName(dto.firstName());
        employeeToUpdate.setLastName(dto.lastName());
        employeeToUpdate.setBirthDate(dto.birthDate());
        employeeToUpdate.setEmail(dto.email());
        employeeToUpdate.setPhone(dto.phone());
        employeeToUpdate.setPosition(position.get());
        employeeToUpdate.setProjects(projects);

        Employee employeeUpdated = employeeRepository.save(employeeToUpdate);

        return new EmployeeVo(
                employeeUpdated.getId(),
                employeeUpdated.getFirstName(),
                employeeUpdated.getLastName(),
                employeeUpdated.getBirthDate(),
                employeeUpdated.getEmail(),
                employeeUpdated.getPhone(),
                employeeUpdated.getPosition(),
                employeeUpdated.getProjects()
        );
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return false;
    }

    @Override
    public EmployeeVo assignProjectToEmployee(Long employeeId, Long projectId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Project> project = projectRepository.findById(projectId);

        if (employee.isEmpty() && project.isEmpty()) {
            throw new RuntimeException("No se encontro el empleado o proyecto");
        }
        Employee employeeToAssign = employee.get();
        Project projectToAssign = project.get();

        employeeToAssign.getProjects().add(projectToAssign);

        Employee employeeSaved = employeeRepository.save(employeeToAssign);

        return new EmployeeVo(
                employeeSaved.getId(),
                employeeSaved.getFirstName(),
                employeeSaved.getLastName(),
                employeeSaved.getBirthDate(),
                employeeSaved.getEmail(),
                employeeSaved.getPhone(),
                employeeSaved.getPosition(),
                employeeSaved.getProjects()
        );
    }
}
