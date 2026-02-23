package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;
    final ObjectMapper objectMapper;

    public Usuario addUsuario(UsuarioRequestDTO usuarioRequestDTO, long roleId) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setSobrenome(usuarioRequestDTO.getSobrenome());
        usuario.setCpf(usuarioRequestDTO.getCpf());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setRoleId(roleId);
        usuario.setStatusId(1);
        usuario.setCriadoEm(new Timestamp(System.currentTimeMillis()));

        return usuarioRepository.save(usuario);
    }

    public void updateUsuario(Usuario usuario, UsuarioRequestDTO usuarioRequestDTO) {

        if (usuarioRequestDTO.getNome() != null && !usuarioRequestDTO.getNome().isBlank()) {
            usuario.setNome(usuarioRequestDTO.getNome());
        }

        if (usuarioRequestDTO.getSobrenome() != null && !usuarioRequestDTO.getSobrenome().isBlank()) {
            usuario.setSobrenome(usuarioRequestDTO.getSobrenome());
        }

        if (usuarioRequestDTO.getCpf() != null) {
            usuario.setCpf(usuarioRequestDTO.getCpf());
        }

        if (usuarioRequestDTO.getEmail() != null) {
            usuario.setEmail(usuarioRequestDTO.getEmail());
        }
    }


}
