package org.example.apihorizonte.dto.nota;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaResponseDTO {
    private Long id;

    private Long alunoDisciplinaId;
    private Long alunoId;
    private String alunoNome;

    private Long disciplinaId;
    private String disciplinaNome;

    private Long professorId;
    private String professorNome;

    private Double valor;
    private String tipo;
}