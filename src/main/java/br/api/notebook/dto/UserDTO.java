package br.api.notebook.dto;

public class UserDTO {
    private String name;
    private String email;
    private int age;
    private String cpf;

    public UserDTO(String name, String email, int age, String cpf) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.cpf = cpf;
    }

    public UserDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
