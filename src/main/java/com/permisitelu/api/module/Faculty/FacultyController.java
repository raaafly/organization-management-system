package com.permisitelu.api.module.Faculty;

import com.permisitelu.api.common.BaseController;
import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Faculties")
@RestController
@RequestMapping(path = "/faculties")
@RequiredArgsConstructor
public class FacultyController implements BaseController<FacultyDTO> {

    private final FacultyService service;

    @Override
    @GetMapping
    public ResponseEntity<ResponseMessage> getAll() {
        List<FacultyDTO> data = service.getFaculties();
        ResponseMessage message = new ResponseMessage("Get All Faculties", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable("id") Long id) {
        FacultyDTO data = service.getFacultyById(id);
        ResponseMessage message = new ResponseMessage("Get Faculty Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody @Valid FacultyDTO object) {
        FacultyDTO data = service.addFaculty(object);
        ResponseMessage message = new ResponseMessage("Add New Faculty Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") Long id, @RequestBody @Valid FacultyDTO object) {
        FacultyDTO data = service.updateFacultyById(id, object);
        ResponseMessage message = new ResponseMessage("Update Faculty Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteFacultyById(id);

        ResponseMessage message = new ResponseMessage("Delete Faculty Id " + id + " Successfully!", HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
