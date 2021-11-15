package br.api.notebook.model;

public class AddressEntityBuilder {
    private Long id;
    private String cep;
    private String street;
    private String district;
    private String city;
    private String state;
    private int number;

    public AddressEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressEntityBuilder withCep(String cep) {
        this.cep = cep;
        return this;
    }

    public AddressEntityBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressEntityBuilder withDistrict(String district) {
        this.district = district;
        return this;
    }

    public AddressEntityBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressEntityBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressEntityBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public AddressEntity build() {
        AddressEntity address = new AddressEntity(id, cep, street, district, city, state, number);

        return address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}