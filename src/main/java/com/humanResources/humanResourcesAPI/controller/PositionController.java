package com.humanResources.humanResourcesAPI.controller;

import com.humanResources.humanResourcesAPI.model.dto.CreatePositionDto;
import com.humanResources.humanResourcesAPI.service.PositionService;
import com.humanResources.humanResourcesAPI.vo.PositionVo;
import com.humanResources.humanResourcesAPI.vo.StandarResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/positions")
public class PositionController {
    private final PositionService positionService;
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findPositionById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
//                .body(positionService.findPositionById(id));
                .body(positionService.findPositionVoById(id));
    }

    @GetMapping
    public ResponseEntity<?>findAllPositions() {
        return ResponseEntity
                .status(HttpStatus.OK)
//                .body(positionService.findAllPositions());
                .body(positionService.findAllPositionVo());
    }

    @PostMapping
    public ResponseEntity<StandarResponse>createPosition(@Valid @RequestBody CreatePositionDto dto){
        PositionVo positionVo = positionService.createPosition(dto);
        StandarResponse standarResponse = StandarResponse
                .builder()
                .message("Position creada exitosamente")
                .data(positionVo)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(standarResponse);
    }
}
