package org.example.apihorizonte.dto.alunoDisciplina;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDisciplinaResponseDTO {
    private Long id;

    private Long alunoId;
    private String alunoNome;

    private Long disciplinaId;
    private String disciplinaNome;
}