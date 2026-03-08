package org.example.apihorizonte.model;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    private String disciplina;

    private Integer bimestre;

    @Column(name = "media_final")
    private Double mediaFinal;
}
