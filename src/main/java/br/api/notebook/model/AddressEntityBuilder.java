package br.api.notebook.model;

public class AddressEntityBuilder {
    private String cep;
    private String street;
    private String district;
    private String city;
    private String state;
    private Long idUser;



    public AddressEntityBuilder withCep (String cep){
        this.cep = cep;
        return this;
    }

    public AddressEntityBuilder withStreet (String street){
        this.street = street;
        return this;
    }

    public AddressEntityBuilder withDistrict (String district){
        this.district = district;
        return this;
    }

    public AddressEntityBuilder withCity(String city){
        this.city = city;
        return this;
    }

    public AddressEntityBuilder withState(String state){
        this.state = state;
        return this;
    }

    public AddressEntityBuilder withIdUser(Long idUser){
        this.idUser = idUser;
        return this;
    }

    public AddressEntity build(){
        AddressEntity address = new AddressEntity(cep, street, district, city, state, idUser);

        return address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
}
