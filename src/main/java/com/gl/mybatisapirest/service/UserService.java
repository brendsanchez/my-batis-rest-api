package com.gl.mybatisapirest.service;

import com.gl.mybatisapirest.converter.impl.UserConverter;
import com.gl.mybatisapirest.dto.UserDto;
import com.gl.mybatisapirest.exception.EmailExistException;
import com.gl.mybatisapirest.exception.UserException;
import com.gl.mybatisapirest.exception.UserNotFoundException;
import com.gl.mybatisapirest.model.User;
import com.gl.mybatisapirest.persistence.DeleteUserSupplier;
import com.gl.mybatisapirest.persistence.InsertUserSupplier;
import com.gl.mybatisapirest.persistence.UpdateUserSupplier;
import com.gl.mybatisapirest.persistence.UsersSupplier;
import com.gl.mybatisapirest.request.UserInsertRequest;
import com.gl.mybatisapirest.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UsersSupplier usersSupplier;
    private final InsertUserSupplier insertUserSupplier;
    private final UpdateUserSupplier updateUserSupplier;
    private final DeleteUserSupplier deleteUserSupplier;

    public List<UserDto> getUsers() throws UserNotFoundException {
        User user = User.builder().build();
        List<User> userList = this.usersSupplier.apply(user);

        if (userList.isEmpty()) {
            throw new UserNotFoundException("users.");
        }

        return this.userConverter.fromModel(userList);
    }

    public UserDto getUserById(final Integer id) throws UserNotFoundException {
        User user = User.builder().id(id).build();
        List<User> userList = this.usersSupplier.apply(user);

        user = userList.stream().findFirst().orElseThrow(() -> new UserNotFoundException("user by: " + id));

        return this.userConverter.fromModel(user);
    }

    public UserDto insertUser(final UserInsertRequest request) throws UserException {
        User user = User.builder().email(request.getEmail()).build();
        List<User> userList = this.usersSupplier.apply(user);

        if (!userList.isEmpty()) {
            throw new EmailExistException(request.getEmail());
        }

        this.insertUserSupplier.test(request);

        user = User.builder()
                .email(request.getEmail())
                .birthday(request.getDate())
                .name(request.getName())
                .surname(request.getSurname())
                .build();

        return this.userConverter.fromModel(user);
    }

    public UserDto updateUser(final UserUpdateRequest request) throws UserNotFoundException {
        User user = User.builder().id(request.getId()).build();
        List<User> userList = this.usersSupplier.apply(user);

        user = userList.stream().findFirst().orElseThrow(() -> new UserNotFoundException("to update by id: " + request.getId()));

        User userRequest = User.builder()
                .id(request.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .birthday(request.getDate())
                .build();

        if (user.equals(userRequest)) {
            throw new UserNotFoundException("difference to update.");
        }

        this.updateUserSupplier.test(request);

        return this.userConverter.fromModel(userRequest);
    }

    public void deleteUserById(final Integer id) throws UserNotFoundException {
        boolean deleteResponse = this.deleteUserSupplier.test(id);

        if (!deleteResponse) {
            throw new UserNotFoundException("user to delete by: " + id);
        }
    }
}
