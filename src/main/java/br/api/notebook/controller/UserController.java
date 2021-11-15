package br.api.notebook.controller;

import br.api.notebook.dto.UserDTO;
import br.api.notebook.model.UserEntity;
import br.api.notebook.security.*;
import br.api.notebook.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<UserEntity>> getUsers(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser() {
        return ResponseEntity.ok().body(userService.findByEmail());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody UserEntity userEntity) throws IOException {
        ValidatorSingleton age = ValidatorSingleton.getInstance(userEntity);
        if (Objects.equals(age.verify(userEntity), ResponseEntity.ok().body("Cadastrado com Sucesso"))) {
            userService.saveUser(userEntity);
        }
        return age.verify(userEntity);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body("Atualizado com Sucesso!");
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("Deletado com Sucesso!");
    }
}
