package com.permisitelu.api.module.position;

import com.permisitelu.api.exception.FoundException;
import com.permisitelu.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final ModelMapper mapper;
    private final PositionRepository repository;

    @Override
    public List<PositionDTO> getPositions() {
        return repository.findAll().stream()
                .map(position -> mapper.map(position, PositionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PositionDTO getPositionById(Long id) {
        Position position = findPositionId(id);
        return mapper.map(position, PositionDTO.class);
    }

    @Override
    public PositionDTO addPosition(PositionDTO object) {
        findPositionName(object);
        Position position = mapper.map(object, Position.class);
        position.setName(object.getName());
        repository.save(position);
        return mapper.map(position, PositionDTO.class);
    }

    @Override
    public PositionDTO updatePositionById(Long id, PositionDTO object) {
        Position position = findPositionId(id);
        findPositionName(object);
        position.setName(object.getName());
        repository.save(position);
        return mapper.map(position, PositionDTO.class);
    }

    @Override
    public void deletePositionById(Long id) {
        Position position = findPositionId(id);
        repository.delete(position);
    }

    private Position findPositionId(Long id) {
        Position position = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Position ID " + id + " Doesn't Exists!"));
        return position;
    }

    private void findPositionName(PositionDTO object) {
        boolean isExists = repository.existsByNameIgnoreCase(object.getName());
        if (isExists) throw new FoundException("Position Name " + object.getName() + " Already Exists!");
    }
}
