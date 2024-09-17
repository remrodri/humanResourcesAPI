package com.humanResources.humanResourcesAPI.repository;

import com.humanResources.humanResourcesAPI.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
}

