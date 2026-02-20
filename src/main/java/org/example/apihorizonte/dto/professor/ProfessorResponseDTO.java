package org.example.apihorizonte.dto.professor;

import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Usuario;
@Getter
@Setter
public class ProfessorResponseDTO {

    private UsuarioResponseDTO usuario;
    private String registroFuncional;

    public ProfessorResponseDTO(UsuarioResponseDTO usuario, String registroFuncional) {
        this.usuario = usuario;
        this.registroFuncional = registroFuncional;
    }
}
