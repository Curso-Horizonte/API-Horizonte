package org.example.apihorizonte.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String senha;
    @Column(name = "role_id")
    private long roleId;
    @Column(name = "status_id")
    private long statusId;
    @Column(name = "criado_em")
    private Timestamp criadoEm;
}