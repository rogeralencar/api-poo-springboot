package br.api.notebook.dto;

import br.api.notebook.model.RoleEntity;

import java.util.Collection;

public class UserDTO {
    private String name;
    private String email;
    private int age;
    private Long cpf;
    private Collection<RoleEntity> roles;

    public UserDTO(String name, String email, int age, Long cpf, Collection<RoleEntity> roles) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.cpf = cpf;
        this.roles = roles;
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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
}
