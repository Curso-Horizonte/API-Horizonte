package org.example.apihorizonte.dto.professor;

import jakarta.validation.constraints.*;
import org.example.apihorizonte.model.Usuario;

import java.time.LocalDate;

public class ProfessorRequestDTO {
    @NotNull(message = "É obrigadtório informar o usuário")
    private Usuario usuario;
    @NotBlank(message = "É obrigatório informar o registro funcional")
    private String registro_funcional;
}
