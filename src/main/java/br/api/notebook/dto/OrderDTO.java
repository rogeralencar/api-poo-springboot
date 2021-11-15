package br.api.notebook.dto;

import br.api.notebook.model.PaymentEntity;

public class OrderDTO {
    private UserDTO user;
    private AddressDTO address;
    private CartItemDTO cart;
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

    public CartItemDTO getCart() {
        return cart;
    }

    public void setCart(CartItemDTO cart) {
        this.cart = cart;
    }

    public PaymentEntity getPay() {
        return pay;
    }

    public void setPay(PaymentEntity pay) {
        this.pay = pay;
    }
}
