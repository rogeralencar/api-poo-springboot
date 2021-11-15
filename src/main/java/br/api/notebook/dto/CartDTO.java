package br.api.notebook.dto;

import br.api.notebook.model.NotebookEntity;
import com.sun.istack.NotNull;

public class CartDTO {
    @NotNull
    private Long quantity;
    @NotNull
    private NotebookEntity notebook;

    @Override
    public String toString(){
        return "'CartDTO'{" +
                ", quantity = " + quantity +
                ", productName = " + notebook.getBrand() +
                " " + notebook.getModel() +
                '}';
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public NotebookEntity getNotebook() {
        return notebook;
    }

    public void setNotebook(NotebookEntity notebook) {
        this.notebook = notebook;
    }
}
