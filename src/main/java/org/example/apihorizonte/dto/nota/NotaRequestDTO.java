package org.example.apihorizonte.dto.nota;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaRequestDTO {
    @NotNull(message = "É obrigatório informar a matrícula do aluno")
    @Positive(message = "O ID da matrícula deve ser positivo")
    private Long alunoDisciplinaId;

    @NotNull(message = "É obrigatório informar o professor")
    @Positive(message = "O ID do professor deve ser positivo")
    private Long professorId;

    @NotNull(message = "É obrigatório informar o valor da nota")
    @DecimalMin(value = "0.0", message = "A nota não pode ser menor que 0")
    @DecimalMax(value = "10.0", message = "A nota não pode ser maior que 10")
    private Double valor;

    @NotBlank(message = "É obrigatório informar o tipo da nota")
    @Size(max = 20, message = "O tipo deve ter no máximo 20 caracteres")
    private String tipo;
}
