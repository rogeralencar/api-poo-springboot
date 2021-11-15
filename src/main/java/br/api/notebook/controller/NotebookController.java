package br.api.notebook.controller;

import br.api.notebook.dto.NotebookDTO;
import br.api.notebook.model.NotebookEntity;
import br.api.notebook.service.NotebookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NotebookController {
    private final NotebookService notebookService;

    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<NotebookDTO>> getNotebooks() {
        return ResponseEntity.ok().body(notebookService.getNotes());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Optional<NotebookEntity>> getNotebooks(@PathVariable Long id) {
        return ResponseEntity.ok().body(notebookService.getNoteById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/note/save")
    public ResponseEntity<String> saveNote(@RequestBody NotebookEntity noteEntity) {
        notebookService.saveNote(noteEntity);
        return ResponseEntity.ok().body("Notebook adicionado com sucesso.");
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/note/update/{id}")
    public ResponseEntity<String> updateNote(@PathVariable Long id, @RequestBody NotebookEntity noteEntity) {
        notebookService.updateNote(noteEntity, id);
        return ResponseEntity.ok().body("Notebook atualizado com Sucesso!");
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/note/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) {
        notebookService.deleteNote(id);
        return ResponseEntity.ok().body("Notebook deletado com Sucesso!");
    }
}