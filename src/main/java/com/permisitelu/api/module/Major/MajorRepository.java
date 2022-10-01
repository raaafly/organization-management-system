package com.permisitelu.api.module.Major;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    boolean existsByNameIgnoreCase(String name);
}