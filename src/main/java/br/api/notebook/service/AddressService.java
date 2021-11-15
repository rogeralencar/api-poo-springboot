package br.api.notebook.service;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface AddressService {
    AddressEntity saveAddress(String cep, int number) throws IOException;
    AddressDTO getAddressDto(AddressEntity addressEntity);
    AddressDTO getByUser();
    AddressEntity updateAddress(AddressEntity addressEntity) throws IOException;
}
