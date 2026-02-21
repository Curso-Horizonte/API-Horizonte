package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.apihorizonte.dto.professor.ProfessorRequestDTO;
import org.example.apihorizonte.dto.professor.ProfessorResponseDTO;
import org.example.apihorizonte.dto.usuario.UsuarioRequestDTO;
import org.example.apihorizonte.dto.usuario.UsuarioResponseDTO;
import org.example.apihorizonte.model.Professor;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.ProfessorRepository;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    final ProfessorRepository professorRepository;
    final UsuarioService usuarioService;
    final UsuarioRepository usuarioRepository;
    final ObjectMapper objectMapper;

    public ProfessorService(ProfessorRepository professorRepository, UsuarioService usuarioService, UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.professorRepository = professorRepository;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    public List<ProfessorResponseDTO> getAllProfessores() {
        List<Professor> professors = professorRepository.findAll();
        List<ProfessorResponseDTO> professorsResponseDTO = new ArrayList<>();

        for (Professor professor : professors) {
            professorsResponseDTO.add(
                    objectMapper.convertValue(professor, ProfessorResponseDTO.class)
            );
        }

        return professorsResponseDTO;
    }

    public ProfessorResponseDTO addProfessor(ProfessorRequestDTO professorRequestDTO) {

        Usuario usuario = usuarioService.addUsuario(professorRequestDTO.getUsuario(), 2L);

        Professor professorModel = new Professor();
        professorModel.setUsuario(usuario);
        professorModel.setRegistroFuncional(professorRequestDTO.getRegistroFuncional());

        Professor professor = professorRepository.save(professorModel);

        return objectMapper.convertValue(professor, ProfessorResponseDTO.class);
    }

    public ProfessorResponseDTO updatePrfessor(Long id, ProfessorRequestDTO professorRequestDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));


        if (professorRequestDTO.getRegistroFuncional() != null) {
            if (professorRequestDTO.getRegistroFuncional().isBlank()) {
                throw new IllegalArgumentException("Registro funcional não pode ser vazio");
            }
            professor.setRegistroFuncional(professorRequestDTO.getRegistroFuncional());
        }

        if (professorRequestDTO.getUsuario() != null) {
            Usuario usuario = professor.getUsuario();
            UsuarioRequestDTO usuarioRequestDTO = professorRequestDTO.getUsuario();

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


        return objectMapper.convertValue(professorRepository.save(professor), ProfessorResponseDTO.class);
    }

    public void removeProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        professorRepository.delete(professor);
    }

}
