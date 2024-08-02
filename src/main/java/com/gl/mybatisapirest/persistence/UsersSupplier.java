package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UsersSupplier implements Function<User, List<User>> {

    private final UserMapper mapper;

    @Override
    public List<User> apply(User user) {
        return mapper.getUsers(user);
    }
}
