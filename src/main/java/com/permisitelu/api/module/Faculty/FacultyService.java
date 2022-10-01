package com.permisitelu.api.module.Faculty;

import java.util.List;

public interface FacultyService {
    List<FacultyDTO> getFaculties();
    FacultyDTO getFacultyById(Long id);
    FacultyDTO addFaculty(FacultyDTO object);
    FacultyDTO updateFacultyById(Long id, FacultyDTO object);
    void deleteFacultyById(Long id);
}
