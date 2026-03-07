package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
