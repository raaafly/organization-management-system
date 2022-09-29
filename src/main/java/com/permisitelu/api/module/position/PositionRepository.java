package com.permisitelu.api.module.position;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    boolean existsByNameIgnoreCase(String name);
}