package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.request.UserInsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InsertUserSupplier implements Function<UserInsertRequest, Boolean> {
    private final UserMapper mapper;

    @Autowired
    public InsertUserSupplier(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Boolean apply(UserInsertRequest request) {
        int totalInsert = this.mapper.insertUser(request);
        return totalInsert > 0;
    }
}
