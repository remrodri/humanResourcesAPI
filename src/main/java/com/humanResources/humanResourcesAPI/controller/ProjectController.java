package com.humanResources.humanResourcesAPI.controller;

import com.humanResources.humanResourcesAPI.model.dto.CreateProjectDto;
import com.humanResources.humanResourcesAPI.service.ProjectService;
import com.humanResources.humanResourcesAPI.vo.ProjectVo;
import com.humanResources.humanResourcesAPI.vo.StandarResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findProjectById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<?>findAllProjects() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<StandarResponse>createProject(@Valid @RequestBody CreateProjectDto dto) {
        ProjectVo projectVo = projectService.createProject(dto);
        StandarResponse standarResponse = StandarResponse
            .builder()
            .message("Projecto creado correctamente")
            .data(projectVo)
            .statusCode(HttpStatus.CREATED.value())
            .build();
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(standarResponse);
    }
}
