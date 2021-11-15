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

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordValidator passValidator;
    private final CpfValidator cpfValidator;
    private final EmailValidator emailValidator;
    private final AgeValidator ageValidator;

    public UserController(UserService userService, PasswordValidator passValidator,
                          CpfValidator cpfValidator, EmailValidator emailValidator, AgeValidator ageValidator) {
        this.userService = userService;
        this.passValidator = passValidator;
        this.cpfValidator = cpfValidator;
        this.emailValidator = emailValidator;
        this.ageValidator = ageValidator;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser(){
        return ResponseEntity.ok().body(userService.findByEmail());
    }



    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody UserEntity userEntity) throws IOException {
        AgeValidatorSingleton age = AgeValidatorSingleton.getInstance(userEntity);
//        System.out.println(age.verifyEmail(userEntity));
//        if(passValidator.isValidPassword(userEntity.getPassword())) {
//            if(emailValidator.isValidEmail(userEntity.getEmail())) {
//                if(cpfValidator.isCpfValid(userEntity.getCpf())){
//                    if(ageValidator.isAgeValid(userEntity.getAge())){
//                        userService.saveUser(userEntity);
//                        return ResponseEntity.ok().body("Cadastrado com Sucesso!");
//                    } else {
//                        return ResponseEntity.badRequest().body("Você precisa ter mais de 18 anos para criar uma conta.");
//                    }
//                } else{
//                    return ResponseEntity.badRequest().body("Seu Cpf deve estar no padrão 000.000.000-00 ou 00000000000");
//                }
//            } else {
//                return ResponseEntity.badRequest().body("Seu email deve conter: \n - @; \n - Um domínio. Ex: .com; \n" +
//                        " - Não ter caracteres especiais; \n - E não deve conter espaços.");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Sua senha deve conter: \n - 8 caracteres; \n - Uma letra maiúscula; \n" +
//                    " - Um caractere especial; \n - E não deve conter espaços.");
//        }
        if(Objects.equals(age.verify(userEntity), ResponseEntity.ok().body("Cadastrado com Sucesso"))){
            userService.saveUser(userEntity);
        }
        return age.verify(userEntity);
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
