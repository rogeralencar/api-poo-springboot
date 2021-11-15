package br.api.notebook.security;

import org.springframework.stereotype.Component;

@Component
public class AgeValidator {
    public boolean isAgeValid(int age){
        return age >= 18;
    }
}
