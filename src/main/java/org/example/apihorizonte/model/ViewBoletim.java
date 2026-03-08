package org.example.apihorizonte.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "view_boletim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewBoletim {

    @Id
    private Long id;

    @Column(name = "aluno_id")
    private Aluno aluno;

    private String disciplina;

    private Integer bimestre;

    @Column(name = "media_final")
    private Double mediaFinal;
}
