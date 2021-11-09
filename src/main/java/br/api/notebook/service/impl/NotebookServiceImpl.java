package br.api.notebook.service.impl;

import br.api.notebook.dto.NotebookDTO;
import br.api.notebook.model.NotebookEntity;
import br.api.notebook.repository.NotebookRepository;
import br.api.notebook.service.NotebookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotebookServiceImpl implements NotebookService {
    private final NotebookRepository notebookRepo;

    public NotebookServiceImpl(NotebookRepository notebookRepo) {
        this.notebookRepo = notebookRepo;
    }

    private NotebookDTO convertEntityToDto(NotebookEntity noteEntity){
        NotebookDTO noteDTO = new NotebookDTO();
        noteDTO.setBrand(noteEntity.getBrand());
        noteDTO.setModel(noteEntity.getModel());
        noteDTO.setRam(noteEntity.getRam());
        noteDTO.setHd(noteEntity.getHd());
        noteDTO.setProcessor(noteEntity.getProcessor());
        noteDTO.setScreen(noteEntity.getScreen());
        noteDTO.setPrice(noteEntity.getPrice());
        return noteDTO;
    }

    @Override
    public List<NotebookDTO> getNotes() {
        return notebookRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotebookEntity> getNoteById(Long id) {
        return notebookRepo.findById(id);
    }

    @Override
    public NotebookEntity saveNote(NotebookEntity noteEntity) {
        return notebookRepo.save(noteEntity);
    }

    @Override
    public NotebookEntity updateNote(NotebookEntity noteEntity) {
        return notebookRepo.save(noteEntity);
    }

    @Override
    public void deleteNote(Long id) {
        notebookRepo.deleteById(id);
    }
}
