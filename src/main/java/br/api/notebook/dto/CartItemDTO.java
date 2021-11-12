package br.api.notebook.dto;

import java.util.List;

public class CartItemDTO {
    private List<CartDTO> cartItems;
    private float totalCost;

    public CartItemDTO(List<CartDTO> cartItems, float totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }

    public List<CartDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
}
