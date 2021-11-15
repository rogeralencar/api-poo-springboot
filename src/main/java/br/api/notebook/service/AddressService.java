package br.api.notebook.service;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface AddressService {
    AddressEntity saveAddress(String cep) throws IOException;
    AddressDTO convertEntityToDto(AddressEntity addressEntity);
    AddressDTO getByUser();
    AddressEntity updateAddress(AddressEntity address) throws IOException;
}
