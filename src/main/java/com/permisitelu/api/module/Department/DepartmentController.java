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
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<DepartmentDTO> departments = service.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable("id") Long id) {
        DepartmentDTO department = service.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<DepartmentDTO> create(@RequestBody @Valid DepartmentDTO object) {
        DepartmentDTO createDepartment = service.addDepartment(object);
        return new ResponseEntity<>(createDepartment, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<DepartmentDTO> update(@PathVariable("id") Long id, @RequestBody @Valid DepartmentDTO object) {
        DepartmentDTO updateDepartment = service.updateDepartmentById(id, object);
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteDepartmentById(id);
        ResponseMessage message = new ResponseMessage("Department ID " + id + " Deleted Successfully!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
