package com.permisitelu.api.module.faculty;

import com.permisitelu.api.exception.FoundException;
import com.permisitelu.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final ModelMapper mapper;
    private final FacultyRepository repository;

    @Override
    public List<FacultyDTO> getFaculties() {
        return repository.findAll().stream()
                .map(faculty -> mapper.map(faculty, FacultyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO getFacultyById(Long id) {
        Faculty faculty = getFaculty(id);
        return mapper.map(faculty, FacultyDTO.class);
    }

    @Override
    public FacultyDTO addFaculty(FacultyDTO object) {
        Faculty faculty = mapper.map(object, Faculty.class);
        checkFacultyName(faculty);
        repository.save(faculty);
        return mapper.map(faculty, FacultyDTO.class);
    }

    @Override
    public FacultyDTO updateFacultyById(Long id, FacultyDTO object) {
        Faculty faculty = getFaculty(id);
        checkFacultyName(faculty);
        faculty.setName(object.getName());
        repository.save(faculty);
        return mapper.map(faculty, FacultyDTO.class);
    }

    @Override
    public void deleteFacultyById(Long id) {
        Faculty faculty = getFaculty(id);
        repository.delete(faculty);
    }


    private Faculty getFaculty(Long id) {
        Faculty faculty = repository.findById(id).orElseThrow(() -> new NotFoundException("Faculty ID " + id + " Doesn't Exists!"));
        return faculty;
    }

    private void checkFacultyName(Faculty faculty) {
        boolean isExists = repository.existsByNameIgnoreCase(faculty.getName());
        if (isExists) throw new FoundException("Faculty Name " + faculty.getName() + " Already Exists!");
    }
}
