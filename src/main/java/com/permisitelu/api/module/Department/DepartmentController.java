package com.permisitelu.api.module.Department;

import com.permisitelu.api.common.BaseController;
import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Departments")
@RestController
@RequestMapping(path = "/departments")
@RequiredArgsConstructor
public class DepartmentController implements BaseController<DepartmentDTO> {
    private final DepartmentService service;

    @Override
    @GetMapping
    public ResponseEntity<ResponseMessage> getAll() {
        List<DepartmentDTO> data = service.getDepartments();
        ResponseMessage message = new ResponseMessage("Get All Departments", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable("id") Long id) {
        DepartmentDTO data = service.getDepartmentById(id);
        ResponseMessage message = new ResponseMessage("Get Department Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody @Valid DepartmentDTO object) {
        DepartmentDTO data = service.addDepartment(object);
        ResponseMessage message = new ResponseMessage("Add New Department Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") Long id, @RequestBody @Valid DepartmentDTO object) {
        DepartmentDTO data = service.updateDepartmentById(id, object);
        ResponseMessage message = new ResponseMessage("Update Department Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteDepartmentById(id);
        ResponseMessage message = new ResponseMessage("Delete Department Id " + id + " Successfully!", HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
