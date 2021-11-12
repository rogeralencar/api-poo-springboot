package br.api.notebook.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notebook_id", referencedColumnName = "id")
    private NotebookEntity notebook;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    private Long quantity;

    public CartEntity(NotebookEntity notebook, UserEntity user, Long quantity) {
        this.notebook = notebook;
        this.user = user;
        this.quantity = quantity;
    }

    public CartEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotebookEntity getNotebook() {
        return notebook;
    }

    public void setNotebook(NotebookEntity notebook) {
        this.notebook = notebook;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
