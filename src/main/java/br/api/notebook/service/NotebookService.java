package br.api.notebook.service;

import br.api.notebook.dto.NotebookDTO;
import br.api.notebook.model.NotebookEntity;

import java.util.List;
import java.util.Optional;

public interface NotebookService {
    NotebookEntity saveNote(NotebookEntity note);
    List<NotebookDTO> getNotes();
    Optional<NotebookEntity> getNoteById(Long id);
    void updateNote(NotebookEntity noteEntity, Long id);
    void deleteNote(Long id);
    NotebookEntity getById(Long id);
}
