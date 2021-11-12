package br.api.notebook.service;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;

import java.io.IOException;
import java.util.List;

public interface AddressService {
    AddressEntity saveAddress(String cep, Long id) throws IOException;
    AddressDTO convertEntityToDto(AddressEntity addressEntity);
    List<AddressDTO> getAddressById(Long id);
}
