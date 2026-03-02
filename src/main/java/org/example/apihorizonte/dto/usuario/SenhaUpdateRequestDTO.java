package org.example.apihorizonte.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaUpdateRequestDTO {
    @NotNull(message = "A senha atual não pode ser nula")
    @Size(min = 6, max = 20, message = "A senha atual tem de 6 à 20 caracteres")
    @NotBlank(message = "A senha atual não pode ser em branco")
    private String senhaAtual;

    @NotNull(message = "A nova senha não pode ser nula")
    @Size(min = 6, max = 20, message = "A nova senha deve ter de 6 à 20 caracteres")
    @NotBlank(message = "A nova senha não pode ser em branco")
    private String novaSenha;
}
