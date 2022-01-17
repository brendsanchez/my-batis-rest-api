package com.gl.mybatisapirest.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
public class UserInsertRequest {
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "username is required")
    private String username;
    @NotEmpty(message = "email is required")
    private String email;
    @NotNull(message = "date is required")
    private Date date;
}
