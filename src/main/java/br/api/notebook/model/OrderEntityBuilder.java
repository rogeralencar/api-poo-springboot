package br.api.notebook.model;

public class OrderEntityBuilder {
    private AddressEntity address;
    private UserEntity user;
    private CartEntity cart;
    private PaymentEntity pay;

    public OrderEntityBuilder withAddress(AddressEntity address){
        this.address = address;
        return this;
    }

    public OrderEntityBuilder withUser(UserEntity user){
        this.user = user;
        return this;
    }

    public OrderEntityBuilder withCart(CartEntity cart){
        this.cart = cart;
        return this;
    }

    public OrderEntityBuilder withPayment(PaymentEntity pay){
        this.pay = pay;
        return this;
    }

    public OrderEntity build(){
        OrderEntity order = new OrderEntity(address, user, pay, cart);
        return order;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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