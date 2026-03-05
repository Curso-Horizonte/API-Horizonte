package org.example.apihorizonte.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.nota.NotaRequestDTO;
import org.example.apihorizonte.dto.nota.NotaResponseDTO;
import org.example.apihorizonte.model.AlunoDisciplina;
import org.example.apihorizonte.model.Nota;
import org.example.apihorizonte.model.Professor;
import org.example.apihorizonte.repository.AlunoDisciplinaRepository;
import org.example.apihorizonte.repository.NotaRepository;
import org.example.apihorizonte.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotaService {

    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final NotaRepository notaRepository;

    @Transactional
    public List<NotaResponseDTO> getAllNotas() {

        return notaRepository.findAllWithRelations()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<NotaResponseDTO> getNotasByAlunoAndDisciplina(Long alunoId, Long disciplinaId) {
        return notaRepository.findNotasByAlunoAndDisciplina(alunoId, disciplinaId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public NotaResponseDTO addNota(NotaRequestDTO dto) {

        AlunoDisciplina alunoDisciplina = alunoDisciplinaRepository
                .findById(dto.getAlunoDisciplinaId())
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada"));

        Professor professor = professorRepository
                .findById(dto.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        Nota nota = new Nota();
        nota.setAlunoDisciplina(alunoDisciplina);
        nota.setProfessor(professor);
        nota.setValor(dto.getValor());
        nota.setTipo(dto.getTipo());

        Nota saved = notaRepository.save(nota);

        return toResponseDTO(saved);
    }

    @Transactional
    public NotaResponseDTO updateNota(Long id, NotaRequestDTO dto) {

        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada"));

        AlunoDisciplina alunoDisciplina = alunoDisciplinaRepository
                .findById(dto.getAlunoDisciplinaId())
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada"));

        Professor professor = professorRepository
                .findById(dto.getProfessorId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        nota.setAlunoDisciplina(alunoDisciplina);
        nota.setProfessor(professor);
        nota.setValor(dto.getValor());
        nota.setTipo(dto.getTipo());

        return toResponseDTO(nota);
    }

    @Transactional
    public void removeNota(Long id) {

        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada"));

        notaRepository.delete(nota);
    }

    private NotaResponseDTO toResponseDTO(Nota nota) {

        NotaResponseDTO dto = new NotaResponseDTO();

        dto.setId(nota.getId());

        dto.setAlunoDisciplinaId(nota.getAlunoDisciplina().getId());
        dto.setAlunoNome(nota.getAlunoDisciplina().getAluno().getUsuario().getNome());

        dto.setProfessorId(nota.getProfessor().getId());
        dto.setProfessorNome(nota.getProfessor().getUsuario().getNome());

        dto.setValor(nota.getValor());
        dto.setTipo(nota.getTipo());

        return dto;
    }

}
