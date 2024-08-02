package com.gl.mybatisapirest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class UserException extends Exception {
    private static final long serialVersionUID = -8086785804188023541L;
    private final String message;
}
