package com.gl.mybatisapirest.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DeleteUserSupplier implements Function<String, Boolean> {
    private final UserMapper mapper;

    @Autowired
    public DeleteUserSupplier(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Boolean apply(String userId) {
        int totalDeleted = this.mapper.deleteUser(userId);

        return totalDeleted > 0;
    }
}
