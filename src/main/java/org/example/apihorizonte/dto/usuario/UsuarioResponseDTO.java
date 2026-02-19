package org.example.apihorizonte.dto.usuario;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private long roleId;
    private long statusId;
    private Timestamp criadoEm;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(String nome, String email, long roleId, long statusId, Timestamp criadoEm) {
        this.nome = nome;
        this.email = email;
        this.roleId = roleId;
        this.statusId = statusId;
        this.criadoEm = criadoEm;
    }
}
