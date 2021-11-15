package br.api.notebook.dto;

import br.api.notebook.model.CartEntity;
import br.api.notebook.model.PaymentEntity;

public class OrderDTO {
    private UserDTO user;
    private AddressDTO address;
    private CartEntity cart;
    private PaymentEntity pay;
    
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public PaymentEntity getPay() {
        return pay;
    }

    public void setPay(PaymentEntity pay) {
        this.pay = pay;
    }
}
