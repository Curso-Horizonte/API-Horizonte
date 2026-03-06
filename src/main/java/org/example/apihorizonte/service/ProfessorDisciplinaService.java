package org.example.apihorizonte.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.professorDisciplina.ProfessorDisciplinaRequestDTO;
import org.example.apihorizonte.dto.professorDisciplina.ProfessorDisciplinaResponseDTO;
import org.example.apihorizonte.model.*;
import org.example.apihorizonte.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfessorDisciplinaService {

    private final ProfessorDisciplinaRepository professorDisciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;

    public List<ProfessorDisciplinaResponseDTO> getAllProfessorDisciplina() {
        return professorDisciplinaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<ProfessorDisciplinaResponseDTO> getAllDisciplinaByProfessorId(Long id) {
        return professorDisciplinaRepository.findAllByProfessorId(id)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProfessorDisciplinaResponseDTO addProfessorDisciplina(
            ProfessorDisciplinaRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));

        boolean exists = professorDisciplinaRepository
                .existsByProfessorIdAndDisciplinaId(
                        dto.getProfessorId(),
                        dto.getDisciplinaId()
                );

        if (exists) {
            throw new RuntimeException("Professor já matriculado nesta disciplina");
        }

        ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
        professorDisciplina.setProfessor(professor);
        professorDisciplina.setDisciplina(disciplina);

        ProfessorDisciplina saved = professorDisciplinaRepository.save(professorDisciplina);

        return toResponseDTO(saved);
    }

    public ProfessorDisciplinaResponseDTO updateProfessorDisciplina(
            Long id,
            ProfessorDisciplinaRequestDTO dto) {

        ProfessorDisciplina professorDisciplina = professorDisciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado nessa disciplina"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));

        professorDisciplina.setProfessor(professor);
        professorDisciplina.setDisciplina(disciplina);

        return toResponseDTO(professorDisciplina);

    }

    public void removeProfessorDisciplina(Long id) {
        ProfessorDisciplina professorDisciplina = professorDisciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        professorDisciplinaRepository.delete(professorDisciplina);
    }

    private ProfessorDisciplinaResponseDTO toResponseDTO(ProfessorDisciplina pd) {

        ProfessorDisciplinaResponseDTO dto = new ProfessorDisciplinaResponseDTO();

        dto.setId(pd.getId());

        dto.setProfessorId(pd.getProfessor().getId());
        dto.setProfessorNome(pd.getProfessor().getUsuario().getNome());

        dto.setDisciplinaId(pd.getDisciplina().getId());
        dto.setDisciplinaNome(pd.getDisciplina().getNome());

        return dto;
    }
}
