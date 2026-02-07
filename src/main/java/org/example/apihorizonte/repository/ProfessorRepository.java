package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
