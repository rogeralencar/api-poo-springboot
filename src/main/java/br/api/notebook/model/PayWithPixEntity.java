package br.api.notebook.model;

import javax.persistence.Entity;

@Entity
public class PayWithPixEntity extends PaymentEntity {
    private String keyPix;

    public PayWithPixEntity() {
        super();
    }

    public String getKeyPix() {
        return keyPix;
    }

    public void setKeyPix(String keyPix) {
        this.keyPix = keyPix;
    }
}