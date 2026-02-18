package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apihorizonte.dto.aluno.AlunoResponseDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    final AlunoRepository alunoRepository;
    final ObjectMapper objectMapper;

    public AlunoService(AlunoRepository alunoRepository, ObjectMapper objectMapper) {
        this.alunoRepository = alunoRepository;
        this.objectMapper = objectMapper;
    }

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
}
