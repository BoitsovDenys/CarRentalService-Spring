package ua.training.car_rental.exeptions;

import org.springframework.security.core.AuthenticationException;

public class NoSuchRoleExeption extends AuthenticationException {
    public NoSuchRoleExeption(String msg, Throwable cause) {
        super(msg, cause);
    }

    public NoSuchRoleExeption(String msg) {
        super(msg);
    }
}
