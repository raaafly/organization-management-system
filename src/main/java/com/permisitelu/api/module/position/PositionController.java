package com.permisitelu.api.module.position;

import com.permisitelu.api.common.BaseController;
import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Positions")
@RestController
@RequestMapping(path = "/positions")
@RequiredArgsConstructor
public class PositionController implements BaseController<PositionDTO> {

    private final PositionService service;

    @Override
    public ResponseEntity<List<PositionDTO>> getAll() {
        List<PositionDTO> positions = service.getPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<PositionDTO> getById(@PathVariable("id") Long id) {
        PositionDTO position = service.getPositionById(id);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PositionDTO> create(@RequestBody @Valid PositionDTO object) {
        PositionDTO createPosition = service.addPosition(object);
        return new ResponseEntity<>(createPosition, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PositionDTO> update(@PathVariable("id") Long id, @RequestBody @Valid PositionDTO object) {
        PositionDTO updatePosition = service.updatePositionById(id, object);
        return new ResponseEntity<>(updatePosition, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deletePositionById(id);
        ResponseMessage message = new ResponseMessage("Position ID " + id + " Deleted Successfully!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
