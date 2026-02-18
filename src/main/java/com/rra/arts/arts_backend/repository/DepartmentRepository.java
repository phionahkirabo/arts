package com.rra.arts.arts_backend.repository;

import com.rra.arts.arts_backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    Optional<Department> findByCode(String code);

    boolean existsByCode(String code);
}