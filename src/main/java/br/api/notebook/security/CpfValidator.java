package br.api.notebook.security;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CpfValidator {
    private final Pattern VALID_CPF_REGEX =
            Pattern.compile("[0-9]{3}[/.]?[0-9]{3}[/.]?[0-9]{3}[-]?[0-9]{2}");

    public Boolean isCpfValid(String cpf){
        Matcher matcher = VALID_CPF_REGEX.matcher(cpf);
        return matcher.find();
    }
}
