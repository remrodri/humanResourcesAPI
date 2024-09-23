package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.dto.CreateProjectDto;
import com.humanResources.humanResourcesAPI.model.entity.Project;
import com.humanResources.humanResourcesAPI.vo.ProjectVo;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Optional<Project> getProjectById(Long id);
//    Optional<ProjectVo> findProjectById(Long id);
    List<Project> getAllProjects();
    ProjectVo createProject(CreateProjectDto dto);
    ProjectVo updateProject(Long id, CreateProjectDto dto);
    boolean deleteProject(Long id);
}
