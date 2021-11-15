package br.api.notebook.service;

import br.api.notebook.dto.AddToCartDTO;
import br.api.notebook.dto.CartDTO;
import br.api.notebook.dto.CartItemDTO;
import br.api.notebook.model.CartEntity;
import br.api.notebook.model.NotebookEntity;
import br.api.notebook.model.UserEntity;

public interface CartService {
    void addToCart(AddToCartDTO addToCartDTO, NotebookEntity notebookEntity, UserEntity userEntity);
    CartItemDTO listItems(UserEntity userEntity);
    void updateItem(AddToCartDTO addToCartDTO, NotebookEntity notebookEntity, UserEntity userEntity);
    void deleteItem(Long id);
    CartEntity getCartByUser(UserEntity user);
}
