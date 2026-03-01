package org.example.apihorizonte.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.observacao.ObservacaoRequestDTO;
import org.example.apihorizonte.dto.observacao.ObservacaoResponseDTO;
import org.example.apihorizonte.model.Aluno;
import org.example.apihorizonte.model.Disciplina;
import org.example.apihorizonte.model.Observacao;
import org.example.apihorizonte.model.Professor;
import org.example.apihorizonte.repository.AlunoRepository;
import org.example.apihorizonte.repository.DisciplinaRepository;
import org.example.apihorizonte.repository.ObservacaoRepository;
import org.example.apihorizonte.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ObservacaoService {
    final ObservacaoRepository observacaoRepository;
    final AlunoRepository alunoRepository;
    final ProfessorRepository professorRepository;
    final DisciplinaRepository disciplinaRepository;

    public List<ObservacaoResponseDTO> getAllObservacoes() {
        return observacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public ObservacaoResponseDTO addObservacao(ObservacaoRequestDTO dto) {

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Observacao observacao = new Observacao();
        observacao.setAluno(aluno);
        observacao.setProfessor(professor);
        observacao.setDisciplina(disciplina);
        observacao.setTexto(dto.getTexto());
        observacao.setData(LocalDateTime.now());

        Observacao saved = observacaoRepository.save(observacao);

        return toResponseDTO(saved);
    }

    @Transactional
    public ObservacaoResponseDTO updateObservacao(Long id, ObservacaoRequestDTO dto) {

        Observacao observacao = observacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observação não encontrada"));

        // Atualiza relações se necessário
        if (dto.getAlunoId() != null) {
            Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            observacao.setAluno(aluno);
        }

        if (dto.getProfessorId() != null) {
            Professor professor = professorRepository.findById(dto.getProfessorId())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            observacao.setProfessor(professor);
        }

        if (dto.getDisciplinaId() != null) {
            Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
            observacao.setDisciplina(disciplina);
        }

        if (dto.getTexto() != null) {
            observacao.setTexto(dto.getTexto());
        }

        Observacao updated = observacaoRepository.save(observacao);

        return toResponseDTO(updated);
    }

    @Transactional
    public void removeObservacao(Long id) {

        Observacao observacao = observacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observação não encontrada"));

        observacaoRepository.delete(observacao);
    }

    private ObservacaoResponseDTO toResponseDTO(Observacao observacao) {

        ObservacaoResponseDTO dto = new ObservacaoResponseDTO();

        dto.setId(observacao.getId());

        dto.setAlunoId(observacao.getAluno().getId());
        dto.setAlunoNome(observacao.getAluno().getNome());

        dto.setProfessorId(observacao.getProfessor().getId());
        dto.setProfessorNome(observacao.getProfessor().getNome());

        dto.setDisciplinaId(observacao.getDisciplina().getId());
        dto.setDisciplinaNome(observacao.getDisciplina().getNome());

        dto.setTexto(observacao.getTexto());
        dto.setData(observacao.getData());

        return dto;
    }
}