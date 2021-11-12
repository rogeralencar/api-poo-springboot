package br.api.notebook.repository;

import br.api.notebook.model.NotebookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookRepository extends CrudRepository<NotebookEntity, Long> {

    @Override
    List<NotebookEntity> findAll();

    NotebookEntity getById(Long aLong);
}
