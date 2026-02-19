package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
        usuario.setRole_id(roleId);
        usuario.setStatus_id(1);
        usuario.setCriado_em(new Timestamp(System.currentTimeMillis()));

        return objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioResponseDTO.class);
    }

}
