package com.humanResources.humanResourcesAPI.repository;

import com.humanResources.humanResourcesAPI.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position, Long> {
}
