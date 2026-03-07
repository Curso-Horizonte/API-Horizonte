package org.example.apihorizonte.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.apihorizonte.dto.documento.DocumentoRequestDTO;
import org.example.apihorizonte.dto.documento.DocumentoResponseDTO;
import org.example.apihorizonte.model.Documento;
import org.example.apihorizonte.model.ProfessorDisciplina;
import org.example.apihorizonte.model.Usuario;
import org.example.apihorizonte.repository.DocumentoRepository;
import org.example.apihorizonte.repository.ProfessorDisciplinaRepository;
import org.example.apihorizonte.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DocumentoService {
    final DocumentoRepository documentoRepository;
    final ProfessorDisciplinaRepository professorDisciplinaRepository;
    final ObjectMapper objectMapper;

    public List<DocumentoResponseDTO> getAllDocumentos() {
        List<Documento> documentos = documentoRepository.findAll();
        List<DocumentoResponseDTO> documentosResponseDTO = new ArrayList<>();

        for (Documento documento : documentos) {
            DocumentoResponseDTO documentoResponseDTO = new DocumentoResponseDTO();

            documentoResponseDTO.setId(documento.getId());
            documentoResponseDTO.setTitulo(documento.getTitulo());
            documentoResponseDTO.setConteudo(documento.getConteudo());
            documentoResponseDTO.setData(documento.getData());
            documentoResponseDTO.setProfessorDisciplinaId(documento.getProfessorDisciplina().getId());

            documentosResponseDTO.add(documentoResponseDTO);
        }

        return documentosResponseDTO;
    }

    public DocumentoResponseDTO addDocumento(DocumentoRequestDTO documentoRequestDTO) {

        ProfessorDisciplina professorDisciplina = professorDisciplinaRepository
                .findById(documentoRequestDTO.getProfessorDisciplinaId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Professor ou disciplina não encontrados")
                );

        Documento documento = new Documento();

        documento.setTitulo(documentoRequestDTO.getTitulo());
        documento.setConteudo(documentoRequestDTO.getConteudo());
        documento.setData(LocalDateTime.now());
        documento.setProfessorDisciplina(professorDisciplina);

        Documento saved = documentoRepository.save(documento);

        DocumentoResponseDTO response = new DocumentoResponseDTO();

        response.setId(saved.getId());
        response.setTitulo(saved.getTitulo());
        response.setConteudo(saved.getConteudo());
        response.setData(saved.getData());
        response.setProfessorDisciplinaId(saved.getProfessorDisciplina().getId());

        return response;
    }

    public DocumentoResponseDTO updateDocumento(Long id, DocumentoRequestDTO documentoRequestDTO) {

        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Documento não encontrado")
                );

        if (documentoRequestDTO.getTitulo() != null) {

            if (documentoRequestDTO.getTitulo().isBlank()) {
                throw new IllegalArgumentException("O título não pode estar vazio");
            }

            documento.setTitulo(documentoRequestDTO.getTitulo());
        }

        if (documentoRequestDTO.getConteudo() != null) {

            if (documentoRequestDTO.getConteudo().isBlank()) {
                throw new IllegalArgumentException("O conteúdo não pode estar vazio");
            }

            documento.setConteudo(documentoRequestDTO.getConteudo());
        }

        if (documentoRequestDTO.getProfessorDisciplinaId() != null) {

            ProfessorDisciplina professorDisciplina = professorDisciplinaRepository
                    .findById(documentoRequestDTO.getProfessorDisciplinaId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Professor ou disciplina não encontrados")
                    );

            documento.setProfessorDisciplina(professorDisciplina);
        }

        Documento saved = documentoRepository.save(documento);

        DocumentoResponseDTO response = new DocumentoResponseDTO();

        response.setId(saved.getId());
        response.setTitulo(saved.getTitulo());
        response.setConteudo(saved.getConteudo());
        response.setData(saved.getData());
        response.setProfessorDisciplinaId(saved.getProfessorDisciplina().getId());

        return response;
    }

    public void removeDocumento(Long id) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Documento não encontrado"));

        documentoRepository.delete(documento);
    }
    
}