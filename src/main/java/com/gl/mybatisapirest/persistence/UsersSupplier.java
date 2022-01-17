package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class UsersSupplier implements Function<User, List<User>> {
    private final UserMapper mapper;

    @Autowired
    public UsersSupplier(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<User> apply(User user) {
        return mapper.getUsers(user);
    }
}
