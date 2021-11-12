package br.api.notebook.model;

import br.api.notebook.dto.AddressDTO;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private int age;
    private Long cpf;
    private String cep;

    @ManyToMany(fetch = EAGER)
    @ColumnDefault("ROLE_USER")
    private Collection<RoleEntity> roleEntities = new ArrayList<>();

    @OneToOne(fetch = EAGER)
    private AddressEntity address;

    public UserEntity(String name, String email, String password, int age, Long cpf,Collection<RoleEntity> roleEntities) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.cpf = cpf;
        this.roleEntities = roleEntities;
    }

    public UserEntity(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Collection<RoleEntity> getRoles() {
        return roleEntities;
    }

    public void setRoles(Collection<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
