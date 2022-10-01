package com.permisitelu.api.module.Role;

import com.permisitelu.api.exception.FoundException;
import com.permisitelu.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final ModelMapper mapper;
    private final RoleRepository repository;

    @Override
    public List<RoleDTO> getRoles() {
        return repository.findAll().stream()
                .map(role -> mapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        Role role = findRoleId(id);
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public RoleDTO addRole(RoleDTO object) {
        Role role = mapper.map(object, Role.class);
        findRoleName(object);
        role.setName(object.getName().toUpperCase());
        repository.save(role);
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public RoleDTO updateRoleById(Long id, RoleDTO object) {
        Role role = findRoleId(id);
        findRoleName(object);
        role.setName(object.getName().toUpperCase());
        repository.save(role);
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public void deleteRoleById(Long id) {
        Role role = findRoleId(id);
        repository.delete(role);
    }

    private Role findRoleId(Long roleId) {
        Role role = repository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role ID " + roleId + " Doesn't Exist!"));
        return role;
    }

    private void findRoleName(RoleDTO object) {
        boolean isExists = repository.existsByNameIgnoreCase(object.getName());
        if (isExists) throw new FoundException("Role Name " + object.getName() + " Already Exists!");
    }
}
