package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.apihorizonte.dto.usuario.*;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    @Value("${senha.padrao}")
    private String senhaPadrao;

    public LoginUsuarioResponseDTO login(LoginUsuarioRequestDTO usuarioRequestDTO) {

        Usuario usuario = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(usuarioRequestDTO.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        UsuarioResponseDTO usuarioResponseDTO =
                objectMapper.convertValue(usuario, UsuarioResponseDTO.class);

        return new LoginUsuarioResponseDTO(usuarioResponseDTO, usuario.getSenha().equals(senhaPadrao));
    }

    public Usuario addUsuario(UsuarioRequestDTO usuarioRequestDTO, long roleId) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setSobrenome(usuarioRequestDTO.getSobrenome());
        usuario.setCpf(usuarioRequestDTO.getCpf());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(senhaPadrao);
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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(senhaUpdateRequestDTO.getSenhaAtual())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        if (senhaUpdateRequestDTO.getNovaSenha().equals(senhaPadrao)) {
            throw new RuntimeException("A nova senha não pode ser igual à senha padrão do sistema");
        }

        if (usuario.getSenha().equals(senhaUpdateRequestDTO.getNovaSenha())) {
            throw new RuntimeException("A nova senha não pode ser igual à senha atual");
        }

        usuario.setSenha(senhaUpdateRequestDTO.getNovaSenha());
        usuarioRepository.save(usuario);
    }
}
