package br.api.notebook.security;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    public static final Pattern VALID_EMAIL_ADRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Boolean isEmailValid(String email){
        Matcher matcher = VALID_EMAIL_ADRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
