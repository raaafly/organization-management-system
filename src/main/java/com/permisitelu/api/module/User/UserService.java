package com.permisitelu.api.module.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getUsers();
    UserDTO getUserById(UUID id);
    UserDTO addUser(UserDTO object);
    UserDTO updateUserById(UUID id, UserDTO object);
    void deleteUserById(UUID id);
}
