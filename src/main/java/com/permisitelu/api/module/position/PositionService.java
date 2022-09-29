package com.permisitelu.api.module.position;

import java.util.List;

public interface PositionService {
    List<PositionDTO> getPositions();
    PositionDTO getPositionById(Long id);
    PositionDTO addPosition(PositionDTO object);
    PositionDTO updatePositionById(Long id, PositionDTO object);
    void deletePositionById(Long id);
}
