package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UpdateUserSupplier implements Function<UserUpdateRequest, Boolean> {
    private final UserMapper mapper;

    @Autowired
    public UpdateUserSupplier(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Boolean apply(UserUpdateRequest request) {
        int totalUpdate = this.mapper.updateUser(request);
        return totalUpdate > 0;
    }
}
