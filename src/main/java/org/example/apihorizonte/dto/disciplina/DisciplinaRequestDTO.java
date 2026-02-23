package org.example.apihorizonte.dto.disciplina;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.validation.OnCreate;

@Getter
@Setter
public class DisciplinaRequestDTO {
    @NotNull(message = "É obrigatório informar o nome", groups = OnCreate.class)
    private String nome;
}
