package com.humanResources.humanResourcesAPI.repository;

import com.humanResources.humanResourcesAPI.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
