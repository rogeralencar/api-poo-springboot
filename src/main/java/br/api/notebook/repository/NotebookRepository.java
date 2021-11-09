package br.api.notebook.repository;

import br.api.notebook.model.NotebookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotebookRepository extends CrudRepository<NotebookEntity, Long> {

    @Override
    List<NotebookEntity> findAll();
}
