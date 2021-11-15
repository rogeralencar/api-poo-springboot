package br.api.notebook.repository;

import br.api.notebook.model.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
    PaymentEntity findByName(String name);
}
