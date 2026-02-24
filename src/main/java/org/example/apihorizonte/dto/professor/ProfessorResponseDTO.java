package org.example.apihorizonte.dto.professor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Usuario;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponseDTO {
    private Long id;
    private UsuarioResponseDTO usuario;
    private String registroFuncional;
}
