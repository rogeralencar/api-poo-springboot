package br.api.notebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "notebook")
public class NotebookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String brand;
    private String model;
    private String description;
    private String ram;
    private String hd;
    private String processor;
    private float screen;
    private float price;

    public NotebookEntity(String brand, String model, String description, String ram, String hd, String processor, float screen, float price) {
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.ram = ram;
        this.hd = hd;
        this.processor = processor;
        this.screen = screen;
        this.price = price;
    }

    public NotebookEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long idNotebook) {
        this.id  = idNotebook;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public float getScreen() {
        return screen;
    }

    public void setScreen(float screen) {
        this.screen = screen;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

