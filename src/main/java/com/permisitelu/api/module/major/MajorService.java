package com.permisitelu.api.module.major;

import java.util.List;

public interface MajorService {
    List<MajorDTO> getMajors();
    MajorDTO getMajorById(Long id);
    MajorDTO addMajor(MajorDTO object);
    MajorDTO updateMajorById(Long id, MajorDTO object);
    void deleteMajorById(Long id);
}
