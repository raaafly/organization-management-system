package com.permisitelu.api.module.Major;

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
public class MajorServiceImpl implements MajorService {

    private final ModelMapper mapper;
    private final MajorRepository repository;

    @Override
    public List<MajorDTO> getMajors() {
        return repository.findAll().stream()
                .map(major -> mapper.map(major, MajorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MajorDTO getMajorById(Long id) {
        Major findId = findMajorId(id);
        return mapper.map(findId, MajorDTO.class);
    }

    @Override
    public MajorDTO addMajor(MajorDTO object) {
        Major major = mapper.map(object, Major.class);
        findMajorName(object);
        major.setName(object.getName());
        repository.save(major);
        return mapper.map(major, MajorDTO.class);
    }

    @Override
    public MajorDTO updateMajorById(Long id, MajorDTO object) {
        Major major = findMajorId(id);
        findMajorName(object);
        major.setName(object.getName());
        repository.save(major);
        return mapper.map(major, MajorDTO.class);
    }

    @Override
    public void deleteMajorById(Long id) {
        Major major = findMajorId(id);
        repository.delete(major);
    }

    private Major findMajorId(Long id) {
        Major major = repository.findById(id).orElseThrow(() -> new NotFoundException("Major ID " + id + " Doesn't Exist!"));
        return major;
    }

    private void findMajorName(MajorDTO object) {
        boolean isExists = repository.existsByNameIgnoreCase(object.getName());
        if(isExists) throw new FoundException("Major Name " + object.getName() + " Already Exists!");
    }
}
