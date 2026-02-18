package org.example.apihorizonte.dto.aluno;

import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Usuario;
@Getter
@Setter
public class AlunoResponseDTO {

    private UsuarioResponseDTO usuario;
    private String matricula;
    public AlunoResponseDTO(UsuarioResponseDTO usuario, String matricula) {
        this.usuario = usuario;
        this.matricula = matricula;
    }
}
