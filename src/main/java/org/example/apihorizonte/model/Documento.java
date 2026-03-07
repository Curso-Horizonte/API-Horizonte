package org.example.apihorizonte.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "documento")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name = "professor_disciplina_id")
    private ProfessorDisciplina professorDisciplina;
}
