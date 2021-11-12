package br.api.notebook.controller;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;
import br.api.notebook.service.AddressService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/cart/address/{id}")
    public List<AddressDTO> getAddress(@PathVariable Long id){
        return addressService.getAddressById(id);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/cart/address")
    public AddressEntity saveAddress(@RequestBody AddressDTO address) throws IOException {
        return addressService.saveAddress(address.getCep(), address.getIdUser());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/cart/address/update")
    public AddressEntity updateAddress(@RequestBody AddressDTO address) throws IOException {
        return addressService.saveAddress(address.getCep(), address.getIdUser());
    }
}
