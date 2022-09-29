package com.permisitelu.api.module.department;

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
public class DepartmentServiceImpl implements DepartmentService {

    private final ModelMapper mapper;
    private final DepartmentRepository repository;

    @Override
    public List<DepartmentDTO> getDepartments() {
        return repository.findAll().stream()
                .map(department -> mapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = findDepartmentId(id);
        return mapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO object) {
        Department department = mapper.map(object, Department.class);
        findDepartmentName(object);
        department.setName(object.getName());
        repository.save(department);
        return mapper.map(department, DepartmentDTO.class);
    }

    @Override
    public DepartmentDTO updateDepartmentById(Long id, DepartmentDTO object) {
        Department department = findDepartmentId(id);
        findDepartmentName(object);
        department.setName(object.getName());
        repository.save(department);
        return mapper.map(department, DepartmentDTO.class);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = findDepartmentId(id);
        repository.delete(department);
    }

    private Department findDepartmentId(Long id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department ID " + id + " Doesn't Exist!"));
        return department;
    }

    private void findDepartmentName(DepartmentDTO object) {
        boolean isExists = repository.existsByNameIgnoreCase(object.getName());
        if (isExists) throw new FoundException("Department Name " + object.getName() + " Already Exists!");
    }
}
