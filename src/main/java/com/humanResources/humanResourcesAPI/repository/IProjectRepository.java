package com.humanResources.humanResourcesAPI.repository;

import com.humanResources.humanResourcesAPI.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long> {
}
