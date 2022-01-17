package com.gl.mybatisapirest.converter.impl;

import com.gl.mybatisapirest.converter.Converter;
import com.gl.mybatisapirest.dto.UserDto;
import com.gl.mybatisapirest.model.User;


public class UserConverter implements Converter<User, UserDto> {
    private static UserConverter instance = null;

    private UserConverter() {
    }

    public static UserConverter getInstance() {
        if (instance == null) {
            instance = new UserConverter();
        }
        return instance;
    }

    @Override
    public User fromDTO(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .date(dto.getDate())
                .build();
    }

    @Override
    public UserDto fromModel(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .date(user.getDate())
                .build();
    }
}
