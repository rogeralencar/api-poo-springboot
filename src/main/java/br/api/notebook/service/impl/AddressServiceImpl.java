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
    public AddressDTO getByUser(){
        UserEntity user = userService.findByEmail();
        AddressEntity address = user.getAddress();
        return convertEntityToDto(address);
    }

    @Override
    public AddressEntity saveAddress(String cep, int number) throws IOException {
        UserEntity user = userService.findByEmail();
        AddressEntityBuilder builder = new AddressEntityBuilder();
        AddressEntity addressEntity = builder.withCity(viaCEP.getEndereco(cep).getLocalidade())
                .withDistrict(viaCEP.getEndereco(cep).getBairro())
                .withState(viaCEP.getEndereco(cep).getUf())
                .withStreet(viaCEP.getEndereco(cep).getLogradouro())
                .withCep(cep)
                .withNumber(number)
                .build();
        user.setAddress(addressEntity);
        addressRepo.save(addressEntity);
        userService.saveUser(user);
        return addressEntity;
    }

    @Override
    public AddressEntity updateAddress(AddressEntity address) throws IOException {
        UserEntity user = userService.findByEmail();
        AddressEntity addressUser = user.getAddress();
        AddressEntityBuilder builder = new AddressEntityBuilder();
        AddressEntity addressEntity = builder.withId(addressUser.getId())
                .withCity(viaCEP.getEndereco(address.getCep()).getLocalidade())
                .withDistrict(viaCEP.getEndereco(address.getCep()).getBairro())
                .withState(viaCEP.getEndereco(address.getCep()).getUf())
                .withStreet(viaCEP.getEndereco(address.getCep()).getLogradouro())
                .withCep(address.getCep())
                .withNumber(address.getNumber())
                .build();
        return addressRepo.save(addressEntity);
    }

    @Override
    public AddressDTO getAddressDto(AddressEntity addressEntity){
        return convertEntityToDto(addressEntity);
    }

    private AddressDTO convertEntityToDto(AddressEntity addressEntity){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(addressEntity.getStreet());
        addressDTO.setDistrict(addressEntity.getDistrict());
        addressDTO.setCity(addressEntity.getCity());
        addressDTO.setState(addressEntity.getState());
        addressDTO.setCep(addressEntity.getCep());
        addressDTO.setNumber(addressEntity.getNumber());
        return addressDTO;
    }
}
