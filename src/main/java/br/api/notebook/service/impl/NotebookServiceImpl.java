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
    public NotebookEntity getById(Long id) {
        return notebookRepo.getById(id);
    }

    @Override
    public NotebookEntity saveNote(NotebookEntity noteEntity) {
        return notebookRepo.save(noteEntity);
    }

    @Override
    public void updateNote(NotebookEntity noteEntity, Long id) {
        Optional<NotebookEntity> note = notebookRepo.findById(id);
        if(note.isPresent()) {
            NotebookEntity n = note.get();
            if (!(noteEntity.getBrand() == null)) {
                n.setBrand(noteEntity.getBrand());
            }
            if (!(noteEntity.getModel() == null)) {
                n.setModel(noteEntity.getModel());
            }
            if (!(noteEntity.getDescription() == null)) {
                n.setDescription(noteEntity.getDescription());
            }
            if (!(noteEntity.getRam() == null)) {
                n.setRam(noteEntity.getRam());
            }
            if (!(noteEntity.getHd() == null)) {
                n.setHd(noteEntity.getHd());
            }
            if (!(noteEntity.getProcessor() == null)) {
                n.setProcessor(noteEntity.getProcessor());
            }
            if (!(noteEntity.getScreen() == 0.0)) {
                n.setScreen(noteEntity.getScreen());
            }
            if (!(noteEntity.getPrice() == 0.0)) {
                n.setPrice(noteEntity.getPrice());
            }
        notebookRepo.save(n);
        }
    }

    @Override
    public void deleteNote(Long id) {
        notebookRepo.deleteById(id);
    }

    private NotebookDTO convertEntityToDto(NotebookEntity noteEntity){
        NotebookDTO noteDTO = new NotebookDTO();
        noteDTO.setBrand(noteEntity.getBrand());
        noteDTO.setModel(noteEntity.getModel());
        noteDTO.setDescription(noteEntity.getDescription());
        noteDTO.setPrice(noteEntity.getPrice());
        return noteDTO;
    }
}
