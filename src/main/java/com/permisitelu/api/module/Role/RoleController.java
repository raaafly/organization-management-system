package com.permisitelu.api.module.Role;

import com.permisitelu.api.common.BaseController;
import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Roles")
@RestController
@RequestMapping(path = "/roles")
@RequiredArgsConstructor
public class RoleController implements BaseController<RoleDTO> {
    private final RoleService service;

    @Override
    @GetMapping
    public ResponseEntity<ResponseMessage> getAll() {
        List<RoleDTO> data = service.getRoles();
        ResponseMessage message = new ResponseMessage("Get All Role", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable("id") Long id) {
        RoleDTO data = service.getRoleById(id);
        ResponseMessage message = new ResponseMessage("Get Role Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody @Valid RoleDTO object) {
        RoleDTO data = service.addRole(object);
        ResponseMessage message = new ResponseMessage("Add New Role Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") Long id, @RequestBody @Valid RoleDTO object) {
        RoleDTO data = service.updateRoleById(id, object);
        ResponseMessage message = new ResponseMessage("Update Role Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteRoleById(id);
        ResponseMessage message = new ResponseMessage("Delete Role Id " + id + " Successfully!", HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
