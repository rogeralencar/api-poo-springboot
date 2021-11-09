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
    public ResponseEntity<List<NotebookDTO>> getNotebooks(){
        return ResponseEntity.ok().body(notebookService.getNotes());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Optional<NotebookEntity>> getNotebooks(@PathVariable Long id){
        return ResponseEntity.ok().body(notebookService.getNoteById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/note/save")
    public ResponseEntity<NotebookEntity> saveNote(@RequestBody NotebookEntity noteEntity){
        return ResponseEntity.ok().body(notebookService.saveNote(noteEntity));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/note/update")
    public ResponseEntity<String> updateNote(@RequestBody NotebookEntity noteEntity){
        notebookService.updateNote(noteEntity);
        return ResponseEntity.ok().body("Atualizado com Sucesso!");
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/note/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id){
        notebookService.deleteNote(id);
        return ResponseEntity.ok().body("Deletado com Sucesso!");
    }
}
