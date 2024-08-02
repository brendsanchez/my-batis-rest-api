package com.gl.mybatisapirest.converter.impl;

import com.gl.mybatisapirest.converter.Converter;
import com.gl.mybatisapirest.dto.UserDto;
import com.gl.mybatisapirest.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserConverter implements Converter<User, UserDto> {

    @Override
    public User fromDTO(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .birthday(dto.getDate())
                .build();
    }

    @Override
    public UserDto fromModel(User user) {
        return UserDto.builder()
                .surname(user.getSurname())
                .name(user.getName())
                .email(user.getEmail())
                .date(user.getBirthday())
                .build();
    }
}
