package com.gl.mybatisapirest.utils;

import com.gl.mybatisapirest.dto.UserDto;
import com.gl.mybatisapirest.model.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestUtil {
    public static final String ID = "1";

    private static final User user1 = User.builder()
            .id(ID)
            .name("goku")
            .surname("kakaroto")
            .email("kamehameha@gmail.com")
            .date(new Date(971210191))
            .build();

    private static final User user2 = User.builder()
            .name("luffy")
            .surname("Monkey")
            .email("gomagoma@gmail.com")
            .date(new Date(957558991))
            .build();

    public static List<User> userList() {
        return Arrays.asList(user1, user2);
    }

    public static UserDto getUserDto1() {
        return UserDto.builder()
                .surname(user1.getSurname())
                .name(user1.getName())
                .email(user1.getEmail())
                .date(user1.getDate())
                .build();
    }

    public static User getUser1() {
        return user1;
    }
}
