package org.example.apihorizonte.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginUsuarioRequestDTO {
    @NotNull(message = "O email não pode ser nulo")
    @Size(min = 5, message = "O email deve ter no mínimo 5 caracteres")
    @NotBlank(message = "O email não pode ser em branco")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 6 à 20 caracteres")
    @NotBlank(message = "A senha não pode ser em branco")
    private String senha;
}
