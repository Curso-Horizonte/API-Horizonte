package org.example.apihorizonte.dto.viewBoletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewBoletimResponseDTO {
    private Long id;

    private Long alunoId;
    private String alunoNome;

    private String disciplina;

    private Integer bimestre;

    private Double mediaFinal;
}