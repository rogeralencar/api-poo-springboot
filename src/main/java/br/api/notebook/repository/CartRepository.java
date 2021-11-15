package br.api.notebook.repository;

import br.api.notebook.model.CartEntity;
import br.api.notebook.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    List<CartEntity> findAllByUser(UserEntity userEntity);
    CartEntity findByUser(UserEntity userEntity);
}
