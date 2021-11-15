package br.api.notebook.model;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AddressEntity address;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserEntity user;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private CartEntity cart;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private PaymentEntity pay;

    public OrderEntity(AddressEntity address, UserEntity user, PaymentEntity pay, CartEntity cart) {
        this.address = address;
        this.user = user;
        this.pay = pay;
        this.cart = cart;
    }

    public OrderEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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