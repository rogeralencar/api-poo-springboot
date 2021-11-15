package br.api.notebook.controller;

import br.api.notebook.dto.AddressDTO;
import br.api.notebook.model.AddressEntity;
import br.api.notebook.service.AddressService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/address")
    public AddressDTO getAddress() {
        return addressService.getByUser();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/address/save")
    public AddressEntity saveAddress(@RequestBody AddressDTO address) throws IOException {
        return addressService.saveAddress(address.getCep(), address.getNumber());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/address/update")
    public AddressEntity updateAddress(@RequestBody AddressEntity address) throws IOException {
        return addressService.updateAddress(address);
    }
}
