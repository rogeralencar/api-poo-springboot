package br.api.notebook.controller;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.UserEntity;
import br.api.notebook.security.PasswordAndEmailValidator;
import br.api.notebook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordAndEmailValidator validator = new PasswordAndEmailValidator();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUsers(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody UserEntity userEntity) throws IOException {
        if(validator.isValidPassword(userEntity.getPassword())) {
            if(validator.isValidEmail(userEntity.getEmail())) {
                userService.saveUser(userEntity);
                return ResponseEntity.ok().body("Cadastrado com Sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Seu email deve conter: \n - @; \n - Um domínio. Ex: .com; \n" +
                        " - Não ter caracteres especiais; \n - E não deve conter espaços.");
            }
        } else {
            return ResponseEntity.badRequest().body("Sua senha deve conter: \n - 8 caracteres; \n - Uma letra maiúscula; \n" +
                    " - Um caractere especial; \n - E não deve conter espaços.");
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user){
        userService.updateUser(user);
        return ResponseEntity.ok().body("Atualizado com Sucesso!");
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("Deletado com Sucesso!");
    }
}
