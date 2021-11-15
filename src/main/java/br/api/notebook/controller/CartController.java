package br.api.notebook.controller;

import br.api.notebook.dto.AddToCartDTO;
import br.api.notebook.dto.CartItemDTO;
import br.api.notebook.model.NotebookEntity;
import br.api.notebook.model.UserEntity;
import br.api.notebook.service.CartService;
import br.api.notebook.service.NotebookService;
import br.api.notebook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final NotebookService noteService;

    public CartController(CartService cartService, UserService userService,
                          NotebookService noteService) {
        this.cartService = cartService;
        this.userService = userService;
        this.noteService = noteService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/cart")
    public CartItemDTO getItems() {
        UserEntity userEntity = userService.findByEmail();
        return cartService.listItems(userEntity);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDTO addToCartDTO) {
        UserEntity userEntity = userService.findByEmail();
        NotebookEntity notebookEntity = noteService.getById(addToCartDTO.getNoteId());
        cartService.addToCart(addToCartDTO, notebookEntity, userEntity);
        return ResponseEntity.ok().body("O item " + notebookEntity.getModel() + " foi adicionado com sucesso ao carrinho.");
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/cart/update")
    public ResponseEntity<String> updateCartItem(@RequestBody AddToCartDTO addToCartDTO) {
        UserEntity userEntity = userService.findByEmail();
        NotebookEntity notebookEntity = noteService.getById(addToCartDTO.getNoteId());
        cartService.updateItem(addToCartDTO, notebookEntity, userEntity);
        return ResponseEntity.ok().body("O item foi atualizado com sucesso no carrinho.");
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        cartService.deleteItem(id);
        return ResponseEntity.ok().body("Item deletado com sucesso.");
    }
}
