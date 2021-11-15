package br.api.notebook.repository;

import br.api.notebook.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Override
    List<OrderEntity> findAll();
}
