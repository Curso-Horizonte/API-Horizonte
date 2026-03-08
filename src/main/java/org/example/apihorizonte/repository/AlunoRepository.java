package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findAlunoByUsuarioId(Long usuarioId);
}
