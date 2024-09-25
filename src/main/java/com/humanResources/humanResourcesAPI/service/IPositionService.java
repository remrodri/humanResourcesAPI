package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.dto.CreatePositionDto;
import com.humanResources.humanResourcesAPI.model.entity.Employee;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.vo.PositionEmployeesVo;
import com.humanResources.humanResourcesAPI.vo.PositionVo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPositionService {
    Optional<Position> findPositionById(Long id);
    List<Position> findAllPositions();
    PositionVo createPosition(CreatePositionDto dto);
    PositionVo updatePosition(Long id, CreatePositionDto dto);
    boolean deletePositionById(Long id);

    List<PositionVo> findAllPositionsVo();
    PositionEmployeesVo findEmployeeByPositionId(Long id);
    PositionVo findPositionVoById(Long id);
    List<PositionVo> findAllPositionVo();
}
