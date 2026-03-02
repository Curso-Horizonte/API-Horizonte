package org.example.apihorizonte.dto.observacao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ObservacaoResponseDTO {
    private Long id;

    private Long alunoId;
    private String alunoNome;

    private Long professorId;
    private String professorNome;

    private Long disciplinaId;
    private String disciplinaNome;

    private String texto;
    private LocalDateTime data;
}