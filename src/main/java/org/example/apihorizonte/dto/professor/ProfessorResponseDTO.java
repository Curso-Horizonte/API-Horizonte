package org.example.apihorizonte.dto.professor;

import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.model.Usuario;
@Getter
@Setter
public class ProfessorResponseDTO {

    private Usuario usuario;
    private String registro_funcional;

    public ProfessorResponseDTO(Usuario usuario, String registro_funcional) {
        this.usuario = usuario;
        this.registro_funcional = registro_funcional;
    }
}
