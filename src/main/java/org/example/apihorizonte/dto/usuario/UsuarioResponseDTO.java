package org.example.apihorizonte.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private long roleId;
    private long statusId;
    private Timestamp criadoEm;

}
