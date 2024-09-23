package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.Exception.EmployeeNotFoundException;
import com.humanResources.humanResourcesAPI.model.dto.CreateProjectDto;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.model.entity.Project;
import com.humanResources.humanResourcesAPI.repository.IProjectRepository;
import com.humanResources.humanResourcesAPI.vo.ProjectVo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;
    private final IEmployeeService employeeService;

    public ProjectService(IProjectRepository projectRepository, IEmployeeService employeeService) {
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectVo createProject(CreateProjectDto dto) {
        Set<Employee> employees = new HashSet<>();
        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescr(dto.descr());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        for (Long employeeId: dto.employeeIds()){
            Optional<Employee> employeeOptional = employeeService.findEmployeeById(employeeId);

            if (employeeOptional.isEmpty()){
                throw new EmployeeNotFoundException("No se encontro el empleado con el id:"+employeeId);
            }

            employees.add(employeeOptional.get());
        }
        project.setEmployees(employees);

        Project savedProject = projectRepository.save(project);

        for (Long employeeId: dto.employeeIds()){
            Optional<Employee> employeeOptional = employeeService.findEmployeeById(employeeId);
            if (employeeOptional.isEmpty()){
                throw new EmployeeNotFoundException("No se encontro el empleado con el id:"+employeeId);
            }
            employeeService.assignProjectToEmployee(employeeId, savedProject.getId());
        }

        return  new ProjectVo(
                savedProject.getId(),
                savedProject.getTitle(),
                savedProject.getTitle(),
                savedProject.getStartDate(),
                savedProject.getEndDate(),
                savedProject.getEmployees()
        );
    }

    @Override
    public ProjectVo updateProject(Long id, CreateProjectDto dto) {
        return null;
    }

    @Override
    public boolean deleteProject(Long id) {
        return false;
    }
}
