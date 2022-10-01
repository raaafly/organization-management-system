package com.permisitelu.api.module.Major;

import com.permisitelu.api.common.BaseController;
import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Majors")
@RestController
@RequestMapping(path = "/majors")
@RequiredArgsConstructor
public class MajorController implements BaseController<MajorDTO> {

    private final MajorService service;

    @Override
    @GetMapping
    public ResponseEntity<List<MajorDTO>> getAll() {
        List<MajorDTO> getMajors = service.getMajors();
        return new ResponseEntity<>(getMajors, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<MajorDTO> getById(@PathVariable("id") Long id) {
        MajorDTO getMajorById = service.getMajorById(id);
        return new ResponseEntity<>(getMajorById, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<MajorDTO> create(@RequestBody @Valid MajorDTO object) {
        MajorDTO createMajor = service.addMajor(object);
        return new ResponseEntity<>(createMajor, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<MajorDTO> update(@PathVariable("id") Long id, @RequestBody @Valid MajorDTO object) {
        MajorDTO updateMajor = service.updateMajorById(id, object);
        return new ResponseEntity<>(updateMajor, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteMajorById(id);
        ResponseMessage message = new ResponseMessage("Major ID " + id + " Deleted Successfully!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
