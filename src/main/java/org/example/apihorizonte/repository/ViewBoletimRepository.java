package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.ViewBoletim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewBoletimRepository extends JpaRepository<ViewBoletim, Long> {

    List<ViewBoletim> findByDisciplina(String disciplina);

    List<ViewBoletim> findByAlunoId(Long alunoId);
}
