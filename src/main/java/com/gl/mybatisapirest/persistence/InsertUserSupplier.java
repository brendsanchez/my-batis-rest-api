package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.request.UserInsertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class InsertUserSupplier implements Predicate<UserInsertRequest> {

    private final UserMapper mapper;

    @Override
    public boolean test(UserInsertRequest request) {
        int totalInsert = this.mapper.insertUser(request);
        return totalInsert > 0;
    }
}
