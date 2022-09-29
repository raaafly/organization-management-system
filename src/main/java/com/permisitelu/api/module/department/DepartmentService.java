package com.permisitelu.api.module.department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getDepartments();
    DepartmentDTO getDepartmentById(Long id);
    DepartmentDTO addDepartment(DepartmentDTO object);
    DepartmentDTO updateDepartmentById(Long id, DepartmentDTO object);
    void deleteDepartmentById(Long id);
}
