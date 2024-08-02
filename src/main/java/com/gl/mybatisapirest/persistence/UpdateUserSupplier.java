package com.gl.mybatisapirest.persistence;

import com.gl.mybatisapirest.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class UpdateUserSupplier implements Predicate<UserUpdateRequest> {

    private final UserMapper mapper;

    @Override
    public boolean test(UserUpdateRequest request) {
        int totalUpdate = this.mapper.updateUser(request);
        return totalUpdate > 0;
    }
}
