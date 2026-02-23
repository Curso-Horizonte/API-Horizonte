package org.example.apihorizonte.dto.aluno;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.validation.OnCreate;

@Getter
@Setter
public class AlunoRequestDTO {
    @NotNull(message = "É obrigatório informar o usuário", groups = OnCreate.class)
    private UsuarioRequestDTO usuario;
    @NotNull(message = "É obrigatório informar a matrícula", groups = OnCreate.class)
    private String matricula;
}
