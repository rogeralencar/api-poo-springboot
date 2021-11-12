package br.api.notebook.repository;

import br.api.notebook.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String Email);

    @Override
    List<UserEntity> findAll();
}
