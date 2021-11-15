package br.api.notebook.model;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class PayWithPixEntity extends PaymentEntity {
    private String keyPix;

    @Override
    public void addKey() {
        String key = UUID.randomUUID().toString();
        setKeyPix(key);
        setName("Pix");
    }

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