package com.gl.mybatisapirest.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
public class UserUpdateRequest {
    @NotNull(message = "id is required")
    private String id;
    private String name;
    private String surname;
    private String email;
    private Date date;
}
