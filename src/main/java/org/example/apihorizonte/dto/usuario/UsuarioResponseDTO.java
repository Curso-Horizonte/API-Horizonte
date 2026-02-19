package org.example.apihorizonte.dto.usuario;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private long role_id;
    private long status_id;
    private Timestamp criado_em;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(String nome, String email, long role_id, long status_id, Timestamp criado_em) {
        this.nome = nome;
        this.email = email;
        this.role_id = role_id;
        this.status_id = status_id;
        this.criado_em = criado_em;
    }
}
