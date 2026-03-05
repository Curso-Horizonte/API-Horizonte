package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.apihorizonte.dto.usuario.*;
import org.example.apihorizonte.exception.IncorrectPasswordeException;
import org.example.apihorizonte.exception.InvalidPasswordException;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    @Value("${senha.padrao}")
    private String senhaPadrao;
    private final PasswordEncoder passwordEncoder;

    public LoginUsuarioResponseDTO login(LoginUsuarioRequestDTO usuarioRequestDTO) {

        Usuario usuario = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(usuarioRequestDTO.getSenha(), usuario.getSenha())) {
            throw new IncorrectPasswordeException("Senha incorreta");
        }

        UsuarioResponseDTO usuarioResponseDTO =
                objectMapper.convertValue(usuario, UsuarioResponseDTO.class);

        return new LoginUsuarioResponseDTO(usuarioResponseDTO, passwordEncoder.matches(senhaPadrao, usuario.getSenha()));
    }

    public Usuario addUsuario(UsuarioRequestDTO usuarioRequestDTO, long roleId) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setSobrenome(usuarioRequestDTO.getSobrenome());
        usuario.setCpf(usuarioRequestDTO.getCpf());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(senhaPadrao));
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

    public void updateSenha(Long usuarioId, SenhaUpdateRequestDTO senhaUpdateRequestDTO) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senhaUpdateRequestDTO.getSenhaAtual(), usuario.getSenha())) {
            throw new IncorrectPasswordeException("Senha atual incorreta");
        }

        if (passwordEncoder.matches(senhaPadrao, senhaUpdateRequestDTO.getNovaSenha())) {
            throw new InvalidPasswordException("A nova senha não pode ser igual à senha padrão do sistema");
        }

        if (usuario.getSenha().equals(senhaUpdateRequestDTO.getNovaSenha())) {
            throw new InvalidPasswordException("A nova senha não pode ser igual à senha atual");
        }

        usuario.setSenha(passwordEncoder.encode(senhaUpdateRequestDTO.getNovaSenha()));
        usuarioRepository.save(usuario);
    }
}
