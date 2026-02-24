package org.example.apihorizonte.dto.professor;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.validation.OnCreate;

import java.time.LocalDate;
@Getter
@Setter
public class ProfessorRequestDTO {
    @NotNull(message = "É obrigatório informar o usuário", groups = OnCreate.class)
    private UsuarioRequestDTO usuario;
    @NotNull(message = "É obrigatório informar o registro funcional", groups = OnCreate.class)
    private String registroFuncional;
}
