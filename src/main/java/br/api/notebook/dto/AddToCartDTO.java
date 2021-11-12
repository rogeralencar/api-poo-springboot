package br.api.notebook.dto;

import com.sun.istack.NotNull;

public class AddToCartDTO {
    private Long id;
    @NotNull
    private Long noteId;
    @NotNull
    private Long quantity;

    public AddToCartDTO(){

    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id =" + id +
                ", productId =" + noteId +
                ", quantity =" + quantity +
                ",";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
