package com.permisitelu.api.module.role;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getRoles();
    RoleDTO getRoleById(Long id);
    RoleDTO addRole(RoleDTO object);
    RoleDTO updateRoleById(Long id, RoleDTO object);
    void deleteRoleById(Long id);
}
