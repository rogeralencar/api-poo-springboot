package br.api.notebook.service.impl;

import br.api.notebook.dto.AddToCartDTO;
import br.api.notebook.dto.CartDTO;
import br.api.notebook.dto.CartItemDTO;
import br.api.notebook.model.CartEntity;
import br.api.notebook.model.NotebookEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.repository.CartRepository;
import br.api.notebook.service.CartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepo;

    public CartServiceImpl(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public CartEntity getCartByUser(UserEntity user){
        return cartRepo.findByUser(user);
    }

    @Override
    public CartItemDTO listItems(UserEntity userEntity) {
        List<CartEntity> cartList = cartRepo.findAllByUser(userEntity);
        List<CartDTO> cartItems = new ArrayList<>();
        float totalCost = 0;
        cartList.forEach(cartEntity -> {
            CartDTO cartDto = convertEntityToDTO(cartEntity);
            cartItems.add(cartDto);
        });
        for (CartDTO cartDto : cartItems){
            totalCost += (cartDto.getNotebook().getPrice() * cartDto.getQuantity());
        }
        return new CartItemDTO(cartItems, totalCost);
    }

    @Override
    public void addToCart(AddToCartDTO addToCartDTO, NotebookEntity notebookEntity, UserEntity userEntity) {
        CartEntity cart = new CartEntity(notebookEntity, userEntity, addToCartDTO.getQuantity());
        cartRepo.save(cart);
    }

    @Override
    public void updateItem(AddToCartDTO addToCartDTO, NotebookEntity notebookEntity, UserEntity userEntity) {
        Optional<CartEntity> cartEntity = cartRepo.findById(addToCartDTO.getId());
        if(cartEntity.isPresent()){
            CartEntity cart = cartEntity.get();
            cart.setQuantity(addToCartDTO.getQuantity());
            cartRepo.save(cart);
        }
    }

    @Override
    public void deleteItem(Long id) {
        cartRepo.deleteById(id);
    }

    private CartDTO convertEntityToDTO(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setQuantity(cartEntity.getQuantity());
        cartDTO.setNotebook(cartEntity.getNotebook());
        return cartDTO;
    }
}
