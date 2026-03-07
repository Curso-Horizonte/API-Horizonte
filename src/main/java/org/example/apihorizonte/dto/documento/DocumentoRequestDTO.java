package org.example.apihorizonte.dto.documento;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.model.ProfessorDisciplina;
import org.example.apihorizonte.validation.OnCreate;

@Getter
@Setter
public class DocumentoRequestDTO {
    @NotNull(message = "É obrigatório informar o título", groups = OnCreate.class)
    private String titulo;
    @NotNull(message = "É obrigatório informar o conteúdo", groups = OnCreate.class)
    private String conteudo;
    @NotNull(message = "É obrigatório informar o professor e a disciplina", groups = OnCreate.class)
    private Long professorDisciplinaId;
}
