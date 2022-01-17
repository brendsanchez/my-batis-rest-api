package com.gl.mybatisapirest.service;

import com.gl.mybatisapirest.dto.UserDto;
import com.gl.mybatisapirest.exception.EmailExistException;
import com.gl.mybatisapirest.exception.UserException;
import com.gl.mybatisapirest.exception.UserNotFoundException;
import com.gl.mybatisapirest.persistence.DeleteUserSupplier;
import com.gl.mybatisapirest.persistence.InsertUserSupplier;
import com.gl.mybatisapirest.persistence.UpdateUserSupplier;
import com.gl.mybatisapirest.persistence.UsersSupplier;
import com.gl.mybatisapirest.request.UserInsertRequest;
import com.gl.mybatisapirest.request.UserUpdateRequest;
import com.gl.mybatisapirest.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UsersSupplier usersSupplier;

    @Mock
    private InsertUserSupplier insertUserSupplier;

    @Mock
    private UpdateUserSupplier updateUserSupplier;

    @Mock
    private DeleteUserSupplier deleteUserSupplier;

    @InjectMocks
    private UserService service;

    @Test
    void getUsersValid() throws UserNotFoundException {
        when(usersSupplier.apply(any())).thenReturn(TestUtil.userList());
        List<UserDto> userDtoList = service.getUsers();

        assertFalse(userDtoList.isEmpty());
        assertEquals(userDtoList.stream().findFirst().get(), TestUtil.getUserDto1());
    }

    @Test
    void getUsersEmpty() {
        when(usersSupplier.apply(any())).thenReturn(Collections.emptyList());
        assertThrows(UserNotFoundException.class, () -> service.getUsers());
    }

    @Test
    void getUserByIdValid() throws UserNotFoundException {
        when(usersSupplier.apply(any())).thenReturn(Collections.singletonList(TestUtil.getUser1()));
        UserDto userDto = service.getUserById(TestUtil.ID);

        assertNotNull(userDto);
        assertEquals(userDto, TestUtil.getUserDto1());
    }

    @Test
    void getUserByIdEmpty() {
        when(usersSupplier.apply(any())).thenReturn(Collections.emptyList());
        assertThrows(UserNotFoundException.class, () -> service.getUserById(TestUtil.ID));
    }

    @Test
    void insertUserValid() throws UserException {
        UserInsertRequest request = UserInsertRequest.builder()
                .name(TestUtil.getUser1().getName())
                .surname(TestUtil.getUser1().getSurname())
                .email(TestUtil.getUser1().getEmail())
                .date(TestUtil.getUser1().getDate())
                .build();

        when(usersSupplier.apply(any())).thenReturn(Collections.emptyList());
        when(insertUserSupplier.test(any(UserInsertRequest.class))).thenReturn(true);

        UserDto userDto = service.insertUser(request);
        assertNotNull(userDto);
        assertEquals(userDto, TestUtil.getUserDto1());
    }

    @Test
    void insertUserEmailExist() {
        UserInsertRequest request = UserInsertRequest.builder().build();

        when(usersSupplier.apply(any())).thenReturn(Collections.singletonList(TestUtil.getUser1()));
        assertThrows(EmailExistException.class, () -> service.insertUser(request));
    }

    @Test
    void updateUserNotFound() {
        UserUpdateRequest request = UserUpdateRequest.builder().build();

        when(usersSupplier.apply(any())).thenReturn(Collections.emptyList());
        assertThrows(UserNotFoundException.class, () -> service.updateUser(request));
    }

    @Test
    void updateUserWithoutDifferencesToUpdate() {
        UserUpdateRequest request = UserUpdateRequest.builder()
                .id(TestUtil.getUser1().getId())
                .name(TestUtil.getUser1().getName())
                .surname(TestUtil.getUser1().getSurname())
                .email(TestUtil.getUser1().getEmail())
                .date(TestUtil.getUser1().getDate())
                .build();

        when(usersSupplier.apply(any())).thenReturn(Collections.singletonList(TestUtil.getUser1()));
        assertThrows(UserNotFoundException.class, () -> service.updateUser(request));
    }

    @Test
    void updateUserValid() throws UserNotFoundException {
        UserUpdateRequest request = UserUpdateRequest.builder()
                .id(TestUtil.getUser1().getId())
                .name("Test")
                .surname(TestUtil.getUser1().getSurname())
                .email(TestUtil.getUser1().getEmail())
                .date(TestUtil.getUser1().getDate())
                .build();

        when(usersSupplier.apply(any())).thenReturn(Collections.singletonList(TestUtil.getUser1()));
        when(updateUserSupplier.test(any(UserUpdateRequest.class))).thenReturn(true);

        UserDto userDto = service.updateUser(request);

        UserDto userDtoEsperado = UserDto.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .date(request.getDate())
                .build();

        assertNotNull(userDto);
        assertEquals(userDto, userDtoEsperado);
    }

    @Test
    void deleteUserValid() throws UserNotFoundException {
        when(deleteUserSupplier.test(any(String.class))).thenReturn(true);
        service.deleteUserById(TestUtil.ID);
    }

    @Test
    void deleteUserNotFound() {
        when(deleteUserSupplier.test(any(String.class))).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> service.deleteUserById(TestUtil.ID));
    }
}