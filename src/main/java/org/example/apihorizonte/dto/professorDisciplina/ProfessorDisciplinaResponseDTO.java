package org.example.apihorizonte.dto.professorDisciplina;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDisciplinaResponseDTO {
    private Long id;

    private Long professorId;
    private String professorNome;

    private Long disciplinaId;
    private String disciplinaNome;
}