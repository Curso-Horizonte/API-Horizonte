package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;
    final ObjectMapper objectMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    public UsuarioResponseDTO addUsuario(UsuarioRequestDTO dto, long roleId) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRoleId(roleId);
        usuario.setStatusId(1);
        usuario.setCriadoEm(new Timestamp(System.currentTimeMillis()));

        return objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

}
