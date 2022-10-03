package com.permisitelu.api.module.User;

import com.permisitelu.api.exception.FoundException;
import com.permisitelu.api.exception.NotFoundException;
import com.permisitelu.api.module.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        User user = findUserId(id);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO addUser(UserDTO object) {
        findUserEmail(object);
        User user = mapper.map(object, User.class);
        userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUserById(UUID id, UserDTO object) {
        return null;
    }

    @Override
    public void deleteUserById(UUID id) {
        User user = findUserId(id);
        userRepository.delete(user);
    }

    private User findUserId(UUID id) {
        return userRepository.findUserById(id).orElseThrow(() ->
                new NotFoundException("User id " + id + " doesn't exists!"));
    }

    private void findUserEmail(UserDTO object) {
        boolean isExists = userRepository.existsUserByEmail(object.getEmail());
        if (isExists) throw new FoundException("User email address is already registered!");
    }
}