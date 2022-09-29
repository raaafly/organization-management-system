package com.permisitelu.api.module.role;

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
    public ResponseEntity<List<RoleDTO>> getAll() {
        List<RoleDTO> roles = service.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable("id") Long id) {
        RoleDTO role = service.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody @Valid RoleDTO object) {
        RoleDTO createRole = service.addRole(object);
        return new ResponseEntity<>(createRole, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable("id") Long id, @RequestBody @Valid RoleDTO object) {
        RoleDTO updateRole = service.updateRoleById(id, object);
        return new ResponseEntity<>(updateRole, HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
        service.deleteRoleById(id);
        ResponseMessage message = new ResponseMessage("Role ID " + id + " Deleted Successfully!");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
