package by.anastasia.demo6.command;

public final class MessageRequest {
    public static final String WRONG_LOGIN = "<br/>incorrect login or path";
    public static final String LOGIN_PHONE_TAKEN = "<br/>login or phone is already taken";
    public static final String INVALID_LOGIN_FORMAT = "<br/>Invalid LOGIN format! Login must contain letters, numbers or '_' sign";
    public static final String INVALID_PASSWORD_FORMAT =  "<br/>Invalid PASSWORD format! Password must contain at least one letter, digit and special character (@$!%*?&). The length must be between 5 and 16 characters.";
    private MessageRequest() {
    }
}
