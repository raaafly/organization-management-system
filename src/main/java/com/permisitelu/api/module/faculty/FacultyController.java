package com.permisitelu.api.module.faculty;

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
    public ResponseEntity<List<FacultyDTO>> getAll() {
        List<FacultyDTO> faculties = service.getFaculties();
        return new ResponseEntity<>(faculties, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<FacultyDTO> getById(@PathVariable("id") Long id) {
        FacultyDTO faculty = service.getFacultyById(id);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<FacultyDTO> create(@RequestBody @Valid FacultyDTO object) {
        FacultyDTO addFaculty = service.addFaculty(object);
        return new ResponseEntity<>(addFaculty, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<FacultyDTO> update(@PathVariable("id") Long id, @RequestBody @Valid FacultyDTO object) {
        FacultyDTO updateFaculty = service.updateFacultyById(id, object);
        return new ResponseEntity<>(updateFaculty, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteFacultyById(id);

        ResponseMessage message = new ResponseMessage("Faculty ID " + id + "Deleted Successfully!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
