package br.api.notebook.service.impl;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;
import br.api.notebook.model.AddressEntityBuilder;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.AddressRepository;
import br.api.notebook.service.AddressService;
import br.api.notebook.service.UserService;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepo;
    private final UserService userService;
    private final ViaCEPClient viaCEP = new ViaCEPClient();

    public AddressServiceImpl(AddressRepository addressRepo, UserService userService) {
        this.addressRepo = addressRepo;
        this.userService = userService;
    }

    @Override
    public AddressEntity saveAddress(String cep, Long id) throws IOException {
        UserEntity user = userService.getUserById(id);
        AddressEntityBuilder builder = new AddressEntityBuilder();
        AddressEntity addressEntity = builder.withCity(viaCEP.getEndereco(cep).getLocalidade())
                .withDistrict(viaCEP.getEndereco(cep).getBairro())
                .withState(viaCEP.getEndereco(cep).getUf())
                .withStreet(viaCEP.getEndereco(cep).getLogradouro())
                .withCep(cep)
                .withIdUser(id)
                .build();
        user.setAddress(addressEntity);
        addressRepo.save(addressEntity);
        userService.saveUser(user);
        return addressEntity;

    }

    @Override
    public AddressDTO convertEntityToDto(AddressEntity addressEntity){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(addressEntity.getStreet());
        addressDTO.setDistrict(addressEntity.getDistrict());
        addressDTO.setCity(addressEntity.getCity());
        addressDTO.setState(addressEntity.getState());
        addressDTO.setCep(addressEntity.getCep());
        addressDTO.setIdUser(addressEntity.getIdUser());
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getAddressById(Long id) {
        return addressRepo.findById(id)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}