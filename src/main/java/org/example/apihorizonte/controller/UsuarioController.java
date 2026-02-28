package org.example.apihorizonte.controller;

import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.usuario.LoginUsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.LoginUsuarioResponseDTO;
import org.example.apihorizonte.dto.usuario.SenhaUpdateRequestDTO;
import org.example.apihorizonte.openapi.UsuarioOpenAPI;
import org.example.apihorizonte.service.UsuarioService;
import org.example.apihorizonte.validation.OnCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController implements UsuarioOpenAPI {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginUsuarioResponseDTO> login(@Validated({OnCreate.class, Default.class})
                                             @RequestBody LoginUsuarioRequestDTO loginUsuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.login(loginUsuarioRequestDTO));
    }

    @PutMapping("/{id}/update/senha")
    public ResponseEntity<Void> updateSenha(
            @PathVariable Long id,
            @RequestBody @Validated SenhaUpdateRequestDTO senhaUpdateRequestDTO
    ) {
        usuarioService.updateSenha(id, senhaUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }
}
