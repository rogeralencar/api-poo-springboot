package br.api.notebook.repository;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
