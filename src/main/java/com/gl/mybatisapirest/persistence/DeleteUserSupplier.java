package com.gl.mybatisapirest.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class DeleteUserSupplier implements Predicate<Integer> {

    private final UserMapper mapper;

    @Override
    public boolean test(Integer userId) {
        int totalDeleted = this.mapper.deleteUser(userId);

        return totalDeleted > 0;
    }
}
