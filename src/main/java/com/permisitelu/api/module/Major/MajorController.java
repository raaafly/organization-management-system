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
    public ResponseEntity<ResponseMessage> getAll() {
        List<MajorDTO> data = service.getMajors();
        ResponseMessage message = new ResponseMessage("Get All Major", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable("id") Long id) {
        MajorDTO data = service.getMajorById(id);
        ResponseMessage message = new ResponseMessage("Get Major Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody @Valid MajorDTO object) {
        MajorDTO data = service.addMajor(object);
        ResponseMessage message = new ResponseMessage("Add New Major Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") Long id, @RequestBody @Valid MajorDTO object) {
        MajorDTO data = service.updateMajorById(id, object);
        ResponseMessage message = new ResponseMessage("Update Major Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteMajorById(id);
        ResponseMessage message = new ResponseMessage("Delete Major Id " + id + " Successfully!", HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
