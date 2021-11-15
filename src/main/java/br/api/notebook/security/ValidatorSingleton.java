package br.api.notebook.security;

import br.api.notebook.model.UserEntity;
import org.springframework.http.ResponseEntity;

public class ValidatorSingleton {
    private static ValidatorSingleton instance;

    private ValidatorSingleton(UserEntity user){
    }

    public static ValidatorSingleton getInstance(UserEntity user){
        if(instance == null){
            instance = new ValidatorSingleton(user);
        }
        return instance;
    }

    public ResponseEntity<String> verify(UserEntity user){
        if(!verifyEmail(user)){
            return ResponseEntity.badRequest().body("Seu email: \n" +
                    "- Deve conter @; \n" +
                    "- Deve conter um domínio. Ex: .com;\n" +
                    " - Não ter caracteres especiais; \n" +
                    "- Não deve conter espaços.");
        } else if(!verifyPassword(user)){
            return ResponseEntity.badRequest().body("Sua senha deve: \n" +
                    "- Conter 8 caracteres; \n" +
                    "- Uma letra maiúscula; \n" +
                    "- Um caractere especial; \n" +
                    "- E não deve conter espaços.");
        } else if(!verifyAge(user)){
            return  ResponseEntity.badRequest().body("Você precisa ter mais de 18 anos para criar uma conta.");
        } else if(!verifyCpf(user)){
            return ResponseEntity.badRequest().body("Seu Cpf deve estar no padrão 000.000.000-00 ou 00000000000");
        }
        return ResponseEntity.ok().body("Cadastrado com Sucesso");
    }

    public boolean verifyEmail(UserEntity user){
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.isEmailValid(user.getEmail());
    }

    public boolean verifyPassword(UserEntity user){
        PasswordValidator passwordValidator = new PasswordValidator();
        return passwordValidator.isPasswordValid(user.getPassword());
    }

    public boolean verifyAge(UserEntity user){
        AgeValidator ageValidator = new AgeValidator();
        return ageValidator.isAgeValid(user.getAge());
    }

    public boolean verifyCpf(UserEntity user){
        CpfValidator cpfValidator = new CpfValidator();
        return cpfValidator.isCpfValid(user.getCpf());
    }
}
