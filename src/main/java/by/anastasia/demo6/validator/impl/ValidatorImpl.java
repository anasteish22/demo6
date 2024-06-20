package by.anastasia.demo6.validator.impl;

import by.anastasia.demo6.validator.Validator;

public class ValidatorImpl implements Validator {
    private static final String LOGIN_REGEX = "\\w{5,25}";
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{5,16}$";
    @Override
    public boolean loginValidate(String login) {
        return login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean passwordValidate(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}
