package com.gl.mybatisapirest.exception;

public class UserNotFoundException extends UserException {
    private static final long serialVersionUID = 2916489645054971735L;

    public UserNotFoundException(String message) {
        super("Not found " + message);
    }
}
