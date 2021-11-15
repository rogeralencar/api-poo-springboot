package br.api.notebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy = JOINED)
public abstract class PaymentEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;

    public PaymentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void addKey();

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