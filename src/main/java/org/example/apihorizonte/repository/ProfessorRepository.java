package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Professor;
import org.example.apihorizonte.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findProfessorByUsuarioId(Long usuarioId);
}
