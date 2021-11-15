package br.api.notebook.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy = JOINED)
public abstract class PaymentEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    public PaymentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PaymentEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}