package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
