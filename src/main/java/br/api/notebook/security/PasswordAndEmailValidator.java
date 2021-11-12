package br.api.notebook.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordAndEmailValidator {
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    public static final Pattern VALID_EMAIL_ADRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Boolean isValidPassword(String password){
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    public Boolean isValidEmail(String email){
        Matcher matcher = VALID_EMAIL_ADRESS_REGEX.matcher(email);
        return matcher.find();
    }
}
