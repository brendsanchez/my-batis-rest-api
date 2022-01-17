package com.gl.mybatisapirest.exception;

public class EmailExistException extends UserException {
    private static final long serialVersionUID = -5426021943478640251L;

    public EmailExistException(String email) {
        super("Email exists: " + email);
    }
}
