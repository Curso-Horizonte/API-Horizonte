package org.example.apihorizonte.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    private String nome;

    @NotNull(message = "O sobrenome não pode ser nulo")
    @NotBlank(message = "O sobrenome não pode ser em branco")
    private String sobrenome;

    @NotNull(message = "O CPF não pode ser nulo")
    @NotBlank(message = "O CPF não pode ser em branco")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    private String cpf;

    @NotNull(message = "O email não pode ser nulo")
    @Size(min = 5, message = "O email deve ter no mínimo 5 caracteres")
    @NotBlank(message = "O email não pode ser em branco")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 6, max = 20, message = "A senha deve ter de 6 a 20 caracteres")
    @NotBlank(message = "A senha não pode ser em branco")
    private String senha;
}
