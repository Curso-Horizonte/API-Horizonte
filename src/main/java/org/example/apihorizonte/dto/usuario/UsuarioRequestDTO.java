package org.example.apihorizonte.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.apihorizonte.validation.OnCreate;
import org.example.apihorizonte.validation.OnPatch;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotNull(message = "O nome não pode ser nulo", groups = {OnCreate.class})
    @NotBlank(message = "O nome não pode ser em branco", groups = {OnCreate.class})
    private String nome;

    @NotNull(message = "O sobrenome não pode ser nulo", groups = {OnCreate.class})
    @NotBlank(message = "O sobrenome não pode ser em branco", groups = {OnCreate.class})
    private String sobrenome;

    @NotNull(message = "O CPF não pode ser nulo", groups = {OnCreate.class})
    @NotBlank(message = "O CPF não pode ser em branco", groups = {OnCreate.class})
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres", groups = {OnCreate.class, OnPatch.class})
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números", groups = {OnCreate.class, OnPatch.class})
    private String cpf;

    @NotNull(message = "O email não pode ser nulo", groups = {OnCreate.class})
    @NotBlank(message = "O email não pode ser em branco", groups = {OnCreate.class})
    @Size(min = 5, message = "O email deve ter no mínimo 5 caracteres", groups = {OnCreate.class, OnPatch.class})
    private String email;

    @NotNull(message = "A senha não pode ser nula", groups = {OnCreate.class})
    @NotBlank(message = "A senha não pode ser em branco", groups = {OnCreate.class})
    @Size(min = 6, max = 20, message = "A senha deve ter de 6 a 20 caracteres", groups = {OnCreate.class})
    private String senha;
}