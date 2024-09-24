package com.humanResources.humanResourcesAPI.service;

import com.humanResources.humanResourcesAPI.model.dto.CreatePositionDto;
import com.humanResources.humanResourcesAPI.model.entity.Position;
import com.humanResources.humanResourcesAPI.vo.PositionVo;

import java.util.List;
import java.util.Optional;

public interface IPositionService {
    Optional<Position> findPositionById(Long id);
    List<Position> findAllPositions();
    PositionVo createPosition(CreatePositionDto dto);
    PositionVo updatePosition(Long id, CreatePositionDto dto);
    boolean deletePositionById(Long id);
    PositionVo findPositionVoById(Long id);
    List<PositionVo> findAllPositionVo();
}
