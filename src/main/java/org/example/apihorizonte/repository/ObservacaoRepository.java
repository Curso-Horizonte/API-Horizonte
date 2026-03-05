package org.example.apihorizonte.repository;

import org.example.apihorizonte.model.Observacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservacaoRepository extends JpaRepository<Observacao, Long> {
}
