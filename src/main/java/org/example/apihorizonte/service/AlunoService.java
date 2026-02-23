package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.example.apihorizonte.dto.aluno.AlunoRequestDTO;
import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.AlunoRepository;
import org.example.apihorizonte.repository.AlunoRepository;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AlunoService {
    final AlunoRepository alunoRepository;
    final UsuarioService usuarioService;
    final UsuarioRepository usuarioRepository;
    final ObjectMapper objectMapper;

    public List<AlunoResponseDTO> getAllAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<AlunoResponseDTO> alunosResponseDTO = new ArrayList<>();

        for (Aluno aluno : alunos) {
            alunosResponseDTO.add(
                    objectMapper.convertValue(aluno, AlunoResponseDTO.class)
            );
        }

        return alunosResponseDTO;
    }

    public AlunoResponseDTO addAluno(AlunoRequestDTO alunoRequestDTO) {

        Usuario usuario = usuarioService.addUsuario(alunoRequestDTO.getUsuario(), 3L);

        Aluno alunoModel = new Aluno();
        alunoModel.setUsuario(usuario);
        alunoModel.setMatricula(alunoRequestDTO.getMatricula());

        Aluno aluno = alunoRepository.save(alunoModel);

        return objectMapper.convertValue(aluno, AlunoResponseDTO.class);
    }

    public AlunoResponseDTO updateAluno(Long id, AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));


        if (alunoRequestDTO.getMatricula() != null) {
            if (alunoRequestDTO.getMatricula().isBlank()) {
                throw new IllegalArgumentException("A matrícula não pode ser vazia");
            }
            aluno.setMatricula(alunoRequestDTO.getMatricula());
        }

        if (alunoRequestDTO.getUsuario() != null) {
            usuarioService.updateUsuario(aluno.getUsuario(), alunoRequestDTO.getUsuario());
        }

        return objectMapper.convertValue(alunoRepository.save(aluno), AlunoResponseDTO.class);
    }

    public void removeAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));

        alunoRepository.delete(aluno);
    }
    
}