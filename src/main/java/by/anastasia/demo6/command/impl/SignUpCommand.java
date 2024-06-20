package by.anastasia.demo6.command.impl;

import by.anastasia.demo6.command.AttributeRequest;
import by.anastasia.demo6.command.MessageRequest;
import by.anastasia.demo6.command.ParameterRequest;
import by.anastasia.demo6.exception.DaoException;
import by.anastasia.demo6.exception.ServiceException;
import by.anastasia.demo6.paths.PagePath;
import by.anastasia.demo6.service.UserService;
import by.anastasia.demo6.service.impl.UserServiceImpl;
import by.anastasia.demo6.command.Command;
import by.anastasia.demo6.model.User;
import by.anastasia.demo6.util.PasswordEncoded;
import by.anastasia.demo6.validator.Validator;
import by.anastasia.demo6.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    private UserService service = new UserServiceImpl();
    private Validator validator = new ValidatorImpl();
    @Override
    public String execute(HttpServletRequest request) throws DaoException, ServiceException {
        String firstName = request.getParameter(ParameterRequest.FIRST_NAME);
        String lastName = request.getParameter(ParameterRequest.LAST_NAME);
        String login = request.getParameter(ParameterRequest.LOGIN);
        String password = request.getParameter(ParameterRequest.PASSWORD);
        String phone = request.getParameter(ParameterRequest.PHONE_NUMBER);

        String page;

        if (validator.passwordValidate(password) && validator.loginValidate(login)) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            password = new PasswordEncoded().encoded(password);
            user.setPassword(password);
            user.setPhoneNumber(phone);

            try {
                if (service.saveUser(user)) {
                    page = PagePath.RESULT;
                } else {
                    request.setAttribute(AttributeRequest.SIGN_UP_MSG, MessageRequest.LOGIN_PHONE_TAKEN);
                    page = PagePath.SIGN_UP;
                }
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
        } else if (!validator.loginValidate(login) && validator.passwordValidate(password)){
            request.setAttribute(AttributeRequest.SIGN_UP_MSG, MessageRequest.INVALID_LOGIN_FORMAT);
            page = PagePath.SIGN_UP;
        } else if (validator.loginValidate(login) && !validator.passwordValidate(password)) {
            request.setAttribute(AttributeRequest.SIGN_UP_MSG, MessageRequest.INVALID_PASSWORD_FORMAT);
            page = PagePath.SIGN_UP;
        } else {
            request.setAttribute(AttributeRequest.SIGN_UP_MSG, MessageRequest.INVALID_LOGIN_FORMAT + MessageRequest.INVALID_PASSWORD_FORMAT);
            page = PagePath.SIGN_UP;
        }
        return page;
    }
}
