package com.gl.mybatisapirest.controller;

import com.gl.mybatisapirest.configuration.SwaggerConfig;
import com.gl.mybatisapirest.dto.UserDto;
import com.gl.mybatisapirest.exception.UserException;
import com.gl.mybatisapirest.request.UserInsertRequest;
import com.gl.mybatisapirest.request.UserUpdateRequest;
import com.gl.mybatisapirest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("user")
@Api(tags = {SwaggerConfig.USER})
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Consulta informacion de todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exito al conseguir todos los usuarios", response = UserDto.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "No se encontraron usuarios"),
            @ApiResponse(code = 500, message = "Error de sistema en la consulta de usuarios")
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() throws UserException {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @ApiOperation(value = "Inserta usuario.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exito al insertar usuario"),
            @ApiResponse(code = 400, message = "Parametro requerido"),
            @ApiResponse(code = 500, message = "Error de sistema al insertar usuario")
    })
    @PostMapping
    public ResponseEntity<UserDto> insertUser(@RequestBody UserInsertRequest request) throws UserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertUser(request));
    }

    @ApiOperation(value = "Update usuario.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exito al editar usuario"),
            @ApiResponse(code = 400, message = "Parametro requerido"),
            @ApiResponse(code = 500, message = "Error de sistema al insertar usuario")
    })
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateRequest request) throws UserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(request));
    }

    @ApiOperation(value = "Consulta informacion de un usuario por id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exito al conseguir usuario"),
            @ApiResponse(code = 400, message = "Parametro requerido"),
            @ApiResponse(code = 500, message = "Error de sistema al conseguir usuario")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) throws UserException {
        return ResponseEntity.ok().body(service.getUserById(id));
    }

    @ApiOperation(value = "Eliminar usuario por id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exito al eliminar usuario"),
            @ApiResponse(code = 400, message = "El id requerido"),
            @ApiResponse(code = 404, message = "No existe usuario con ese Id para eliminar"),
            @ApiResponse(code = 500, message = "Error de sistema al conseguir usuario")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable String id) throws UserException {
        this.service.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
