package com.permisitelu.api.module.User;

import com.permisitelu.api.common.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Api(tags = "Users")
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<ResponseMessage> getAll() {
        List<UserDTO> data = service.getUsers();
        ResponseMessage message = new ResponseMessage("Get All Users", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable("id") UUID id) {
        UserDTO data = service.getUserById(id);
        ResponseMessage message = new ResponseMessage("Get User Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody @Valid UserDTO object) {
        UserDTO data = service.addUser(object);
        ResponseMessage message = new ResponseMessage("Add New User Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") UUID id, @RequestBody @Valid UserDTO object) {
        UserDTO data = service.updateUserById(id, object);
        ResponseMessage message = new ResponseMessage("Update User Id " + id + " Successfully!", data, HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") UUID id) {
        service.deleteUserById(id);
        ResponseMessage message = new ResponseMessage("Delete User Id " + id + " Successfully!", HttpStatus.OK);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
