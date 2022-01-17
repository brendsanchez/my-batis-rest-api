package com.gl.mybatisapirest.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class DeleteUserSupplier implements Predicate<String> {
    private final UserMapper mapper;

    @Autowired
    public DeleteUserSupplier(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean test(String userId) {
        int totalDeleted = this.mapper.deleteUser(userId);

        return totalDeleted > 0;
    }
}
